package logic.room

import akka.actor.{ActorSystem, Actor, Props, ActorRef}
import logic.room.GameEvents.{StartGame, GameStarted}

/**
 * User: Dmytro Vynokurov
 * Date: 1/14/14
 * Time: 1:38 AM
 */
class Room extends Actor{

  val system = ActorSystem("actorSystem")

  def playGame() = {
    val players = getPlayer :: getPlayer :: getPlayer :: Nil
    val game = system.actorOf(Props[Game],"game")
    game ! StartGame(players)
  }

  def getPlayer:ActorRef = system.actorOf(Props[Player],"player"+IdGenerator.nextId)

  object IdGenerator{
    private var counter=0
    def nextId = {
      counter=counter+1
      counter
    }
  }

  def receive: Actor.Receive = {
    case _ => playGame()
  }
}

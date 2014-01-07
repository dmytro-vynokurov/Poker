package logic.room

import logic.game._
import akka.actor.{ActorRef, Actor}
import logic.room.GameEvents._
import logic.room.Blind._


/**
 * User: Dmytro Vynokurov
 * Date: 11/22/13
 * Time: 1:30 AM
 */
case class Player(name: String, game: ActorRef) extends Actor{
  var pocket: Pocket = EmptyPocket
  var table: Table = EmptyTable
  var playerMap:Map[ActorRef,PlayerRef] = Map()
  var blindAmount: Int = 0

  //TODO: create logic for AI and business logic for user
  def makeDecision(hand: Hand):DecisionEvent = ???

  def sendEvent(e:Event) = {
    for(player<-playerMap.keys) player ! e
    game ! e
  }

  def receive: Actor.Receive = {

    case StartGame(p) => playerMap = p

    case SetBlindAmount(ba) => blindAmount = ba

    case PutBlind(b) => b match {
      case SMALL => sendEvent(Bet(blindAmount))
      case BIG => sendEvent(Bet(2*blindAmount))
    }

    case DealPocket(p) => pocket=p

    case DealTable(t) => {
      table = t
      discardBets(playerMap)
    }

    case Bet(amount) => registerBet(playerMap,sender,amount)

    case Call(amount) => registerBet(playerMap,sender,amount)

    case Fold => registerFold(playerMap,sender)

    case MakeDecision => {
      val hand = Hand(pocket,table)
      sendEvent(makeDecision(hand))
    }

  }
}

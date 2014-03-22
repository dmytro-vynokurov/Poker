package logic.room

import logic.game.{EmptyTable, Pocket, Deck, Table}
import akka.actor.{ActorRef, Actor}
import logic.room.GameEvents._
import Blind._

/**
 * User: Dmytro Vynokurov
 * Date: 11/22/13
 * Time: 1:33 AM
  */
case class Game(ordering:List[ActorRef],playerNames:List[String],blindAmount: Int) extends Actor{
  require(ordering.size>=2)
  require(ordering.size == playerNames.size)

  import RoundDecision._

  val deck = Deck()
  var table:Table = EmptyTable


  var playerMap: Map[ActorRef,PlayerRef] = Map(
    ordering.head -> PlayerRef(playerNames.head,SMALL),
    ordering.tail.head -> PlayerRef(playerNames.tail.head,BIG)
  )
  for((actorRef,name)<-ordering.zip(playerNames).tail.tail) playerMap = playerMap + ((actorRef,PlayerRef(name)))

  def play = {
    startGame
    putBlinds
    dealPocket
    placeBets match {
      case CONTINUE => dealFlop; placeBets match{
        case CONTINUE => dealTurn; placeBets match{
          case CONTINUE => dealRiver; placeBets
        }
      }
    }
  }

  def startGame = {
    for(player<-ordering) {
      player ! GameStarted(playerMap)
      player ! SetBlindAmount(blindAmount)
    }
  }

  def putBlinds = {
    ordering.head ! PutBlind(SMALL)
    ordering.tail.head ! PutBlind(BIG)
  }

  def dealPocket = {
    for(player<-ordering) player ! DealPocket(Pocket(deck.dealCard,deck.dealCard))
  }

  def placeBets:RoundDecision = {
    def nextPlayer(player:ActorRef):ActorRef = ordering.dropWhile(_ != player).drop(1).find(playerMap(_).inGame) match {
      case Some(playerRef) => playerRef
      case None => ordering.takeWhile(_!=player).find(playerMap(_).inGame).get
    }
    def stopRound:Boolean = {
      val inGamePlayers = ordering.filter(playerMap(_).inGame)
      if (inGamePlayers.size == 1) return true
      val commonDecision = playerMap(inGamePlayers.head).lastDecision
      inGamePlayers.map(playerMap(_)).forall(_.lastDecision==commonDecision)
    }

    var player = ordering.head
    while(!stopRound){
      player = nextPlayer(player)
      player ! MakeDecision
      receive
    }
    if(playerMap.values.count(_.inGame)==1) STOP
    else CONTINUE
  }

  def dealFlop = {
    table = Table(deck.dealCard::deck.dealCard::deck.dealCard::Nil)
    for(player<-ordering) player ! DealFlop(table)
  }

  def dealTurn = {
    val oldTableCards = table.cards
    table = Table(deck.dealCard::oldTableCards)
    for(player<-ordering) player ! DealTurn(table)
  }

  def dealRiver = {
    val oldTableCards = table.cards
    table = Table(deck.dealCard::oldTableCards)
    for(player<-ordering) player ! DealRiver(table)
  }

  def receive: Actor.Receive = {

    case Bet(amount) => registerBet(playerMap,sender,amount)

    case Call(amount) => registerCall(playerMap,sender,amount)

    case Check => registerFold(playerMap,sender)

    case Fold => registerFold(playerMap,sender)
  }
}

object RoundDecision extends Enumeration{
  type RoundDecision = Value
  val STOP,CONTINUE = Value
}


package logic.room


import logic.game.{Table, Pocket}
import akka.actor.{ActorRef, Actor}
import logic.room.Blind.Blind

/**
 * User: Dmytro Vynokurov
 * Date: 11/24/13
 * Time: 3:13 PM
 */
object GameEvents {

  sealed trait Event

  case class StartGame(players: Map[ActorRef,PlayerRef]) extends Event
  case class SetBlindAmount(blindAmount: Int) extends Event
  case class PutBlind(blind: Blind) extends Event
  case object MakeDecision extends Event

  case class DealPocket(pocket: Pocket) extends Event

  class DealTable(val table: Table) extends Event
  object DealTable{
    def apply(table:Table) = new DealTable(table)
    def unapply(dt:DealTable):Option[Table] = Some(dt.table)
  }
  case class DealFlop(override val table: Table) extends DealTable(table)
  case class DealTurn(override val table: Table) extends DealTable(table)
  case class DealRiver(override val table: Table) extends DealTable(table)



  sealed class DecisionEvent extends GameEvents.Event

  case class Bet(amount: Int) extends DecisionEvent
  case class Call(amount: Int) extends DecisionEvent
  case object Check extends DecisionEvent
  case object Fold extends DecisionEvent



  def registerBet(playerMap:Map[ActorRef,PlayerRef],player: ActorRef, amount: Int) = playerMap(player).lastBet = amount

  def registerFold(playerMap:Map[ActorRef,PlayerRef],player: ActorRef) = playerMap(player).inGame = false

  def discardBets(playerMap:Map[ActorRef,PlayerRef]) = for(playerRef<-playerMap.values) playerRef.lastBet = 0

}
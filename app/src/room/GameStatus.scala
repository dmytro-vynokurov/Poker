package src.room

/**
 * User: Dmytro Vynokurov
 * Date: 11/23/13
 * Time: 2:45 AM
 */
object GameStatus extends Enumeration{
  type GameStatus = Value
  val NOGAME,PREFLOP,FLOP,TURN,RIVER = Value
}

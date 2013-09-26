package model

/**
 * User: Dmitry
 * Date: 8/19/13
 * Time: 10:12 PM
 */
sealed abstract class Suit

object Suit{
  val all = Spades :: Clubs :: Hearts :: Diamonds :: Nil
}

object Spades extends Suit{
  override def toString: String = "Spades"
}
object Clubs extends Suit {
  override def toString: String = "Clubs"
}
object Hearts extends Suit{
  override def toString: String = "Hearts"
}
object Diamonds extends Suit{
  override def toString: String = "Diamonds"
}



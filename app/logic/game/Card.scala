package logic.game


/**
 * User: Dmitry
 * Date: 8/19/13
 * Time: 10:03 PM
 */
case class Card(number: Number,suit: Suit) extends Ordered[Card]{
  def compare(that: Card): Int = this.number compare that.number
  override def toString:String = s"$number of $suit"
}




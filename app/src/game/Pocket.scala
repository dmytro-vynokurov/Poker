package src.game

/**
 * User: Dmitry
 * Date: 8/22/13
 * Time: 9:53 PM
 */
case class Pocket(cards: List[Card]){
  def this(first:Card, second: Card) = this(first :: second :: Nil)

  require(cards.size<=2)
  require(cards.size>=0)

  def isEmpty = cards.isEmpty
  def withCard(card: Card):Pocket = Pocket(card::cards)
  def first = cards.head
  def second = cards.tail.head
}
object Pocket{
  def apply(first:Card, second: Card) = new Pocket(first,second)
}
object EmptyPocket extends Pocket(Nil)


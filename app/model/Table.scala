package model

/**
 * User: Dmitry
 * Date: 8/19/13
 * Time: 11:42 PM
 */
case class Table(cards: List[Card]) {
  require(cards.size <= 5)

  def isEmpty = cards.size == 0
}



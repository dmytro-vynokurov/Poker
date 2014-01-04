package src.game

import scala.util.Random


/**
 * User: Dmytro Vynokurov
 * Date: 11/22/13
 * Time: 1:48 AM
 */
class Deck {
  var cards:Set[Card] = Random.shuffle(for {
    suit <- Suit.all
    number <- Number.all
  } yield number of suit).toSet[Card]

  def dealCard:Card = {
    if (cards.isEmpty) throw new IllegalStateException("No cards left in a deck")

    val top = cards.head
    cards = cards.tail
    top
  }
}
object Deck{
  def apply() = new Deck
}

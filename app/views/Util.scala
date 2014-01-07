package views

import logic.game.Card

/**
 * User: Dmytro Vynokurov
 * Date: 1/8/14
 * Time: 1:15 AM
 */
object Util {
  implicit class CardFilename(card: Card){
    override def toString: String = s"${card.number}Of${card.suit}"
  }
}

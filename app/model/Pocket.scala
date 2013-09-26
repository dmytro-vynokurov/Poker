package model

/**
 * User: Dmitry
 * Date: 8/22/13
 * Time: 9:53 PM
 */
case class Pocket(first:Card, second: Card) {
  def cards = first :: second :: Nil
}

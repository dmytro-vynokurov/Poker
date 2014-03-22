package logic.game

import org.specs2.mutable.Specification
import logic.game.Number._
import logic.game.Suit._
import logic.game.Combination._

/**
 * User: Dmitry
 * Date: 8/25/13
 * Time: 12:44 PM
 */
class TableTest extends Specification {
  "The 'Table' class" should{
    " - fail when receiving list of more than 5 cards in a constructor" in {
      Table(
          (Three of Spades) ::
          (Three of Diamonds)::
          (Four of Diamonds)::
          (Four of Spades)::
          (Four of Hearts)::
          (Four of Clubs)::
          Nil) must throwA[IllegalArgumentException]
    }

    " - create a new instance when passed a list of less than 6 cards" in {
      val cards =
          (Three of Spades) ::
          (Three of Diamonds) ::
          (Four of Diamonds) ::
          (Four of Spades) ::
          (Four of Hearts) ::
          Nil

      val table = Table(cards)

      table.cards === cards
    }
  }
}

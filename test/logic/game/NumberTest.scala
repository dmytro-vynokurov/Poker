package logic.game

import org.specs2.mutable.Specification
import logic.game.Number._
import logic.game.Suit._
import logic.game.Combination._

/**
 * User: Dmitry
 * Date: 8/25/13
 * Time: 5:27 PM
 */
class NumberTest extends Specification {
  "The 'Number' class" should{
    " - show that same numbers are equal" in{
      val first = Nine
      val second = Nine

      first === second
    }

    " - show that different numbers are not equal" in{
      val first = Nine
      val second = Ace

      first mustNotEqual second
    }
  }

}

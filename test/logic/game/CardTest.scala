package src.game

import org.specs2.mutable.Specification


/**
 * User: Dmitry
 * Date: 8/24/13
 * Time: 11:41 PM
 */
class CardTest extends Specification{

  "The 'Card' class" should {
    " - use apply method when passed card number and suit respectively" in {
      Card(Three,Spades) must beEqualTo(new Card(Three,Spades))
    }
  }

}

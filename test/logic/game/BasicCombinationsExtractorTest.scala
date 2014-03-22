package logic.game

import org.specs2.mutable.Specification
import BasicCombinationsExtractor._
import logic.game.Number._
import logic.game.Suit._
import logic.game.Combination._

/**
 * User: Dmitry
 * Date: 8/25/13
 * Time: 1:23 PM
 */
class BasicCombinationsExtractorTest extends Specification {
  "The 'extractFlush' method" should {
    " - not find flush when less than 5 cards received" in {
      val cards =
          (Three of Spades) ::
          (Four of Spades) ::
          (Five of Spades) ::
          (Six of Spades) ::
          Nil

      extractFlush(cards) === (None,cards)
    }

    " - find flush when 5 cards of same suit are present" in {
      val cards =
          (Three of Spades) ::
          (Four of Spades) ::
          (Five of Spades) ::
          (Six of Spades) ::
          (Seven of Spades) ::
          Nil

      extractFlush(cards) === (Some(Flush(cards.map(_.number).reverse)),Nil)
    }

    " - find top flush when more than 5 cards of the same suit are present" in {
      val cards =
          (Five of Spades) ::
          (Seven of Spades) ::
          (Three of Spades) ::
          (Jack of Spades) ::
          (Four of Spades) ::
          (Six of Spades) ::
          Nil

      val expectedFlushCombination =
        Jack :: Seven :: Six :: Five :: Four :: Nil

      extractFlush(cards) === (Some(Flush(expectedFlushCombination)),(Three of Spades) :: Nil)
    }
  }

  "The 'extractNumber' method" should{
    " - not find any combination if combination of less than 2 cards is searched for" in{
      val cards = (Three of Spades) :: Nil
      extractNumber(cards,1) === (None,cards)
    }

    " - not find any combination if combination of more than 4 cards is searched for" in{
      val cards =
        (Five of Spades) ::
          (Seven of Spades) ::
          (Three of Spades) ::
          (Jack of Spades) ::
          (Four of Spades) ::
          (Six of Spades) ::
          Nil

      extractNumber(cards,5) === (None,cards)
    }

    " - not find any combination if there are less cards than in a searched combination" in{
      val cards = (Three of Spades):: (Three of Diamonds) :: Nil
      extractNumber(cards,3) === (None,cards)
    }

    " - find a Pair if a pair is present in a list" in {
      val cards =
        (Three of Spades) ::
        (Three of Clubs) ::
        (Four of Diamonds) ::
        Nil

      extractNumber(cards,2) === (Some(Pair(Three)), (Four of Diamonds) :: Nil)
    }

    " - find ThreeOfAKind if it is present in a list" in {
      val cards =
        (Three of Spades) ::
        (Three of Clubs) ::
        (Three of Diamonds) ::
        (Four of Diamonds) ::
        Nil

      extractNumber(cards,3) === (Some(ThreeOfAKind(Three)), (Four of Diamonds) :: Nil)
    }

    " - find FourOfAKind if it is present in a list" in {
      val cards =
        (Three of Spades) ::
        (Three of Clubs) ::
        (Three of Diamonds) ::
        (Three of Hearts) ::
        (Four of Diamonds) ::
        Nil

      extractNumber(cards,4) === (Some(FourOfAKind(Three)), (Four of Diamonds) :: Nil)
    }
    "  - return rest of the cards in descending order" in{
      val cards = List(Five of Spades, Five of Hearts, Seven of Diamonds, Five of Diamonds, Nine of Hearts)

      extractNumber(cards,3) === (Some(ThreeOfAKind(Five)),List(Nine of Hearts, Seven of Diamonds))
    }
  }

  "The 'extractStraight' method" should{
    " - not find straight when less than 5 cards received" in{
      val cards =
         (Three of Spades) ::
         (Four of Spades) ::
         (Five of Spades) ::
         (Six of Spades) ::
         Nil

      extractStraight(cards) === (None,cards)
    }

    " - find straight when 5 cards are sorted" in {
      val cards =
          (Three of Spades) ::
          (Four of Clubs) ::
          (Five of Diamonds) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          Nil

      extractStraight(cards) === (Some(Straight(Seven)),Nil)
    }

    " - find top straight when more than 5 cards are sorted" in {
      val cards =
          (Three of Spades) ::
          (Four of Clubs) ::
          (Five of Diamonds) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          (Eight of Spades) ::
          Nil

      extractStraight(cards) === (Some(Straight(Eight)),(Three of Spades) :: Nil)
    }

    " - find straight if first 2 cards do not match" in {
      val cards =
          (Two of Clubs) ::
          (Three of Spades) ::
          (Five of Diamonds) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          (Eight of Hearts) ::
          (Nine of Spades) ::
          Nil

      extractStraight(cards) === (Some(Straight(Nine)), List(Three of Spades, Two of Clubs))
    }

    " - find straight if there are 2 cards with same number" in {
      val cards =
          (Three of Spades) ::
          (Four of Clubs) ::
          (Five of Diamonds) ::
          (Five of Hearts) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          Nil

      extractStraight(cards) === (Some(Straight(Seven)), List(Five of Hearts))
    }

    " - find top straight if there are 2 cards with same number " in {
      val cards =
          (Three of Spades) ::
          (Four of Clubs) ::
          (Five of Diamonds) ::
          (Five of Hearts) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          (Eight of Spades) ::
          Nil

      extractStraight(cards) === (Some(Straight(Eight)), List(Five of Hearts,Three of Spades))
    }

    " - find straight if the last card does not match" in {
      val cards =
          (Three of Spades) ::
          (Four of Clubs) ::
          (Five of Diamonds) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          (Ace of Spades) ::
          Nil

      extractStraight(cards) === (Some(Straight(Seven)), (Ace of Spades) :: Nil)
    }

    " - find straight if the last and the first card do not match" in {
      val cards =
          (Two of Spades) ::
          (Four of Clubs) ::
          (Five of Diamonds) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          (Eight of Spades) ::
          (Ace of Spades) ::
          Nil

      extractStraight(cards) === (Some(Straight(Eight)), (Ace of Spades) :: (Two of Spades) :: Nil)
    }

    " - not find straight if there is none (5 cards)" in{
      val cards =
          (Three of Spades) ::
          (Four of Clubs) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          (Eight of Spades) ::
          Nil

      extractStraight(cards) === (None,cards)
    }

    " - not find straight if there is none (6 cards)" in{
      val cards =
          (Three of Spades) ::
          (Four of Clubs) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          (Eight of Spades) ::
          (Jack of Spades) ::
          Nil

      extractStraight(cards) === (None,cards)
    }

    " - not find straight if there is none (7 cards)" in{
      val cards =
          (Three of Spades) ::
          (Four of Clubs) ::
          (Six of Diamonds) ::
          (Seven of Spades) ::
          (Eight of Spades) ::
          (King of Hearts) ::
          (Ace of Spades) ::
          Nil

      extractStraight(cards) === (None,cards)
    }


  }
}

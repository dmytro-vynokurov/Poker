package model

import org.specs2.mutable.Specification

/**
 * User: Dmitry
 * Date: 8/25/13
 * Time: 11:12 PM
 */
class CombinationsExtractor$Test extends Specification {
  "The 'extractCombinations' method" should{
    "  - extract straight from list of cards" in{
      val hand = Hand(Pocket(Five of Spades, Six of Hearts),Table(List(Seven of Diamonds, Eight of Diamonds, Nine of Hearts)))

      hand.combinations === Straight(Nine) :: Nil
    }

    "  - extract straight flush from list of cards" in{
      val hand = Hand(Pocket(Five of Spades, Six of Spades),Table(List(Seven of Spades, Eight of Spades, Nine of Spades)))

      hand.combinations === StraightFlush(Nine) :: Nil
    }

    "  - extract flush from list of cards" in{
      val hand = Hand(Pocket(Five of Spades, Queen of Spades),Table(List(Seven of Spades, Ace of Spades, Nine of Spades)))

      hand.combinations === Flush(List(Ace,Queen,Nine,Seven,Five)) :: Nil
    }

    "  - extract four of a kind from list of cards" in{
      val hand = Hand(Pocket(Five of Spades, Five of Hearts),Table(List(Five of Diamonds, Ace of Spades, Five of Clubs)))

      hand.combinations === FourOfAKind(Five):: HighCard(Ace) :: Nil
    }

    "  - extract three of a kind from list of cards" in{
      val hand = Hand(Pocket(Five of Spades, Six of Hearts),Table(List(Five of Diamonds, Ace of Spades, Five of Clubs)))

      hand.combinations === ThreeOfAKind(Five):: HighCard(Ace):: HighCard(Six) :: Nil
    }

    "  - extract full house from a list of cards" in{
      val hand = Hand(Pocket(Five of Spades, Three of Hearts),Table(List(Five of Diamonds, Three of Spades, Five of Clubs)))

      hand.combinations === FullHouse(Five,Three) :: Nil
    }

    "  - extract two pairs from a list of cards" in{
      val hand = Hand(Pocket(Five of Spades, Ace of Hearts),Table(List(Three of Diamonds, Three of Spades, Five of Clubs)))

      hand.combinations === TwoPairs(Five,Three) :: HighCard(Ace) :: Nil
    }

    "  - extract two pairs from a list of cards in reverse order" in{
      val hand = Hand(Pocket(Three of Spades, Ace of Hearts),Table(List(Five of Diamonds, Five of Spades, Three of Clubs)))

      hand.combinations === TwoPairs(Five,Three) :: HighCard(Ace) :: Nil
    }

    "  - extract a pair from a list of cards" in{
      val hand = Hand(Pocket(Three of Spades, Ace of Hearts),Table(List(Jack of Diamonds, Five of Spades, Three of Clubs)))

      hand.combinations === Pair(Three) :: HighCard(Ace) :: HighCard(Jack) :: HighCard(Five) :: Nil
    }

    "  - extract high card from a list of cards" in{
      val hand = Hand(Pocket(Queen of Spades, Ace of Hearts),Table(List(Jack of Diamonds, Five of Spades, Three of Clubs)))

      hand.combinations === HighCard(Ace) :: HighCard(Queen) :: HighCard(Jack) :: HighCard(Five) :: HighCard(Three) :: Nil
    }
  }

  "The 'extractFromPocket' method" should{


    "  - extract a pair from a list of cards when table is empty" in{
      val hand = Hand(Pocket(Three of Spades, Three of Hearts),Table(List()))

      hand.combinations === Pair(Three) :: Nil
    }

    "  - extract a high card from a list of cards when table is empty" in{
      val hand = Hand(Pocket(Five of Spades, Three of Hearts),Table(List()))

      hand.combinations === HighCard(Five) :: HighCard(Three) :: Nil
    }
  }
}

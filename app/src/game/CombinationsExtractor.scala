package src.game

import src.game.BasicCombinationsExtractor._
import scala.Some

/**
 * User: Dmitry
 * Date: 8/25/13
 * Time: 11:10 PM
 */
object CombinationsExtractor {

  def extractFromPocket(pocket: Pocket): List[Combination] = (pocket.first,pocket.second) match{
    case (a: Card,b: Card) if a.number == b.number => Pair(a.number) :: Nil
    case (a: Card,b: Card) if a > b => HighCard(a.number) :: HighCard(b.number) :: Nil
    case (a: Card,b: Card) if a < b => HighCard(b.number) :: HighCard(a.number) :: Nil
  }

  def extractCombinations(cards: List[Card]): List[Combination] = {
    extractStraight(cards) match {
      case (None, cardsRemaining1) => extractWhenStraightNotMatched(cardsRemaining1)
      case (Some(straight), _) => straight :: Nil
    }
  }

  private def extractWhenStraightNotMatched(cards: List[Card]): List[Combination] = {
    extractFlush(cards) match {
      case (None, cardsRemaining) => extractWhenFlushNotMatched(cardsRemaining)
      case (Some(flush), _) => flush :: Nil
    }
  }

  private def extractWhenFlushNotMatched(cards: List[Card]): List[Combination] = {
    extractNumber(cards, 4) match {
      case (None, cardsRemaining) => extractWhenFourOfAKindNotMatched(cardsRemaining)
      case (Some(four), cardsRemaining) => four :: HighCard(cardsRemaining.head.number) :: Nil
    }
  }

  private def extractWhenFourOfAKindNotMatched(cards: List[Card]): List[Combination] = {
    (extractNumber(cards, 3): @unchecked) match {
      case (None, cardsRemaining) => extractWhenThreeOfAKindNotMatched(cardsRemaining)
      case (Some(three: ThreeOfAKind), cardsRemaining) => extractWhenThreeOfAKindMatched(cardsRemaining, three)
    }
  }

  private def extractWhenThreeOfAKindMatched(cards: List[Card], three: ThreeOfAKind): List[Combination] = {
    (extractNumber(cards, 2): @unchecked) match {
      case (None, cardsRemaining) =>
        three :: HighCard(cardsRemaining.head.number) :: HighCard(cardsRemaining.tail.head.number) :: Nil
      case (Some(pair: Pair), _) => FullHouse(three.number, pair.number) :: Nil
    }
  }

  private def extractWhenThreeOfAKindNotMatched(cards: List[Card]): List[Combination] = {
    (extractNumber(cards, 2): @unchecked) match {
      case (None, cardsRemaining) => (HighCard(cardsRemaining.head.number) ::
        HighCard(cardsRemaining.tail.head.number) ::
        HighCard(cardsRemaining.tail.tail.head.number) ::
        HighCard(cardsRemaining.tail.tail.tail.head.number) ::
        HighCard(cardsRemaining.tail.tail.tail.tail.head.number) :: Nil).sortWith((a, b) => a.compare(b) > 0)
      case (Some(pair1: Pair), cardsRemaining) => extractWhenPairMatched(cardsRemaining, pair1)
    }
  }

  private def extractWhenPairMatched(cards: List[Card], pair: Pair): List[Combination] = {
    (extractNumber(cards, 2): @unchecked) match {
      case (None, cardsRemaining) => pair ::
        HighCard(cardsRemaining.head.number) ::
        HighCard(cardsRemaining.tail.head.number) ::
        HighCard(cardsRemaining.tail.tail.head.number) :: Nil
      case (Some(pair2: Pair), cardsRemaining) => TwoPairs(pair.number, pair2.number) ::
        HighCard(cardsRemaining.head.number) :: Nil
    }
  }
}

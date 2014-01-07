package logic.game

import CombinationsExtractor._

/**
 * User: Dmitry
 * Date: 8/19/13
 * Time: 11:37 PM
 */
case class Hand(pocket: Pocket, table: Table) {
  val cards: List[Card] = (pocket.cards ::: table.cards).sorted
  def combinations: List[Combination] = {
    if (table.isEmpty) extractFromPocket(pocket)
    else extractCombinations(cards)
  }
}


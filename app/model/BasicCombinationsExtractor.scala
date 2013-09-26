package model

/**
 * User: Dmitry
 * Date: 8/25/13
 * Time: 1:22 PM
 */
object BasicCombinationsExtractor {

  def extractStraight(cards: List[Card]): (Option[Combination],List[Card]) = {
    if (cards.size < Straight.cardsUsed) return (None,cards)

    var cardsRemaining = cards.sorted
    var gathered: List[Card] = Nil
    var cardsMissed: List[Card] = Nil

    while(cardsRemaining.size + gathered.size >= Straight.cardsUsed){
      if (cardsRemaining.isEmpty) return (Some(getStraightOfGatheredCards(gathered)),
                                              cardsMissed ::: gathered.drop(Straight.cardsUsed))

      if (gathered.isEmpty || (cardsRemaining.head.number - gathered.head.number == 1)){
        gathered ::= cardsRemaining.head
      }else if(cardsRemaining.head.number == gathered.head.number) {
        cardsMissed ::= cardsRemaining.head
      }else if(gathered.size >= Straight.cardsUsed){
        return (Some(getStraightOfGatheredCards(gathered)),
                    cardsRemaining ::: cardsMissed ::: gathered.drop(Straight.cardsUsed))
      }else {
        cardsMissed :::= gathered
        gathered = cardsRemaining.head :: Nil
      }

      cardsRemaining = cardsRemaining.tail
    }

    (None,cards)
  }

  private def getStraightOfGatheredCards(gathered: List[Card]): Combination = {
    val head = gathered.head
    if (gathered.drop(1).take(Straight.cardsUsed).forall(_.suit == head.suit)) StraightFlush(head.number)
    else Straight(head.number)
  }

  def extractFlush(cards: List[Card]): (Option[Flush], List[Card]) = {
    if (cards.size < Flush.cardsUsed) return (None, cards)

    for {
      suit <- Suit.all
      if cards.count(_.suit == suit) >= 5
    } return (Some(Flush(cards.filter(_.suit == suit).sortWith((a,b)=>a.compare(b)>0).map(_.number).take(5))),
      cards.filter(_.suit != suit) ::: cards.filter(_.suit == suit).sortWith((a,b)=>a.compare(b)>0).drop(5))

    (None, cards)
  }

  def extractNumber(cards: List[Card], cardsInCombination: Int): (Option[Combination], List[Card]) = {
    if((cardsInCombination > 4) || (cardsInCombination < 2) || (cards.size < cardsInCombination)) return (None, cards)

    for {
      number <- Number.all
      if cards.count(_.number == number) == cardsInCombination
    } return (Some(getCombinationOfSameCards(cardsInCombination, number)),
      cards.filter(_.number != number).sortWith((a,b)=>a.compare(b)>0))

    (None, cards)
  }

  private def getCombinationOfSameCards(cardsInCombination: Int, cardValue: Number): Combination = {
    cardsInCombination match {
      case 4 => FourOfAKind(cardValue)
      case 3 => ThreeOfAKind(cardValue)
      case 2 => Pair(cardValue)
    }
  }

}

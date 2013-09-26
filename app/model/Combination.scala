package model

/**
 * User: Dmitry
 * Date: 8/19/13
 * Time: 10:20 PM
 */
sealed abstract class Combination extends Ordered[Combination] {
  def priority = 0
  def cardsUsed = 0

  def compare(that: Combination) = {
    if (this.priority != that.priority) this.priority - that.priority
    else {
      (this,that) match {
        case (a:HighCard,b:HighCard) => a.number-b.number
        case (a:Pair,b:Pair) => a.number-b.number
        case (a:TwoPairs,b:TwoPairs) => if(a.higher==b.higher) a.lower-b.lower else a.higher-b.higher
        case (a:ThreeOfAKind,b:ThreeOfAKind) => a.number - b.number
        case (a:Straight,b:Straight) => a.top - b.top
        case (a:Flush,b:Flush) => if(a.highest!=b.highest) a.highest-b.highest
                                  else if(a.second!=b.second) a.second-b.second
                                    else if(a.third!=b.third) a.third-b.third
                                      else if(a.fourth!=b.fourth) a.fourth-b.fourth
                                        else a.fifth - b.fifth
        case (a:FullHouse,b:FullHouse) => if(a.threes==b.threes)a.twos-b.twos else a.threes-b.threes
        case (a:FourOfAKind,b:FourOfAKind) => a.number-b.number
        case (a:StraightFlush,b:StraightFlush) => a.top-b.top
        case (a,b) => throw new IllegalArgumentException(s"Cannot match arguments of types ${a.getClass} and ${b.getClass}")
      }
    }
  }
}

case class HighCard(number: Number) extends Combination {
  override def priority = HighCard.priority
  override def cardsUsed = HighCard.cardsUsed
}
object HighCard{
  val priority = 1
  val cardsUsed = 1
}

case class Pair(number: Number) extends Combination {
  override def priority = Pair.priority
  override def cardsUsed = Pair.cardsUsed
}
object Pair{
  val priority = 2
  val cardsUsed = 2
}

case class TwoPairs(higher: Number, lower: Number) extends Combination {
  require(higher != lower)

  override def priority = TwoPairs.priority
  override def cardsUsed = TwoPairs.cardsUsed
}
object TwoPairs{
  val priority = 3
  val cardsUsed = 4
}

case class ThreeOfAKind(number: Number) extends Combination {
  override def priority = ThreeOfAKind.priority
  override def cardsUsed = ThreeOfAKind.cardsUsed
}
object ThreeOfAKind{
  val priority = 4
  val cardsUsed = 3
}

case class Straight(top: Number) extends Combination {
  require(top >= Five)

  override def priority = Straight.priority
  override def cardsUsed = Straight.cardsUsed
}
object Straight{
  val priority = 5
  val cardsUsed = 5
}

case class Flush(numbers: List[Number]) extends Combination {
  require(numbers.size == 5)

  override def priority = Flush.priority
  override def cardsUsed = Flush.cardsUsed

  private val numbersSorted = numbers.sorted

  val highest:Number = numbersSorted(0)
  val second:Number = numbersSorted(1)
  val third:Number = numbersSorted(2)
  val fourth:Number = numbersSorted(3)
  val fifth:Number = numbersSorted(4)
}
object Flush{
  val priority = 6
  val cardsUsed = 5
}

case class FullHouse(threes: Number, twos: Number) extends Combination {
  require(twos != threes)

  override def priority = FullHouse.priority
  override def cardsUsed = FullHouse.cardsUsed
}
object FullHouse{
  val priority = 7
  val cardsUsed = 5
}

case class FourOfAKind(number: Number) extends Combination {
  override def priority = FourOfAKind.priority
  override def cardsUsed = FourOfAKind.cardsUsed
}
object FourOfAKind{
  val priority = 8
  val cardsUsed = 4
}

case class StraightFlush(top: Number) extends Combination {
  require(top >= Five)

  override def priority = StraightFlush.priority
  override def cardsUsed = StraightFlush.cardsUsed
}
object StraightFlush{
  val priority = 9
  val cardsUsed = 5
}
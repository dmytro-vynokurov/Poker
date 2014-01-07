package logic.game


/**
 * User: Dmitry
 * Date: 8/19/13
 * Time: 10:15 PM
 */

sealed abstract class Number(val name: String, val value: Int) extends Ordered[Number]{
  def compare(that: Number): Int = this.value - that.value
  def of(suit: Suit): Card = Card(this,suit)
  override def toString: String = name
}
object Number{
  val all: List[Number] = Ace :: King :: Queen :: Jack :: Ten :: Nine :: Eight ::
    Seven :: Six :: Five :: Four :: Three :: Two :: Nil

  implicit def number2Int(number: Number) = number.value
  implicit def int2Number(i: Int) = i match {
    case 2 => Two
    case 3 => Three
    case 4 => Four
    case 5 => Five
    case 6 => Six
    case 7 => Seven
    case 8 => Eight
    case 9 => Nine
    case 10 => Ten
    case 11 => Jack
    case 12 => Queen
    case 13 => King
    case 14 => Ace
    case _ => throw new IllegalArgumentException("Wrong index for a card")
  }

  object Ace extends Number("Ace",14)

  object King extends Number("King",13)

  object Queen extends Number("Queen",12)

  object Jack extends Number("Jack",11)

  object Ten extends Number("Ten",10)

  object Nine extends Number("Nine",9)

  object Eight extends Number("Eight",8)

  object Seven extends Number("Seven",7)

  object Six extends Number("Six",6)

  object Five extends Number("Five",5)

  object Four extends Number("Four",4)

  object Three extends Number("Three",3)

  object Two extends Number("Two",2)
}
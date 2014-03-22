package views

import logic.game.{Pocket, Card}
import logic.room.Blind.Blind
import logic.room.Blind
import logic.room.Blind._

/**
 * User: Dmytro Vynokurov
 * Date: 1/8/14
 * Time: 1:15 AM
 */
object Util {
  class CardFilename(filename:String){
    override def toString: String = filename
  }
  implicit def OptionCard2CardFilename(card:Option[Card]):CardFilename = card match{
    case Some(c) => new CardFilename(s"${c.number}Of${c.suit}")
    case None => new CardFilename("Back")
  }
  implicit def Card2CardFilename(card:Card) = new CardFilename(s"${card.number}Of${card.suit}")

  class PlayerView(val name:String,val pocket:Option[Pocket],val action:String,val amount:Int,val blind:Blind){
    val (card1,card2) = pocket match{
      case Some(p) => (Some(p.first),Some(p.second))
      case None => (None,None)
    }
    val blindShort = blind match {
      case SMALL => "SB"
      case BIG => "BB"
      case NO_BLIND => ""
    }
  }

}

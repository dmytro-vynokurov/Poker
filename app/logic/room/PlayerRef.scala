package logic.room

import logic.room.Blind.Blind

/**
 * User: Dmytro Vynokurov
 * Date: 12/11/13
 * Time: 10:08 PM
 */
class PlayerRef(val name:String,var inGame:Boolean,var lastBet:Int,val blind: Blind = Blind.NO_BLIND)
object PlayerRef{
  def apply(name:String,blind:Blind = Blind.NO_BLIND) = new PlayerRef(name,true,0,blind)
}

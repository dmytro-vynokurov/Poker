package logic.room

import logic.room.Blind.Blind
import logic.room.GameEvents.{NotActedYet, Decision}
import akka.actor.{ActorPath, ActorRef}

/**
 * User: Dmytro Vynokurov
 * Date: 12/11/13
 * Time: 10:08 PM
 */
class PlayerRef(val name:String,var inGame:Boolean,val blind: Blind = Blind.NO_BLIND,var lastDecision:Decision)
object PlayerRef{
  def apply(name:String,blind:Blind = Blind.NO_BLIND) = new PlayerRef(name,true,blind,NotActedYet)
}

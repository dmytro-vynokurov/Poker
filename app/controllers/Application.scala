package controllers

import play.api.mvc._
import logic.game.Suit._
import logic.game.Number._

object Application extends Controller {

  def index = Action {
    val templateRendered = views.html.index(List(Ace of Spades,Two of Clubs))
    Ok(templateRendered)
  }


}
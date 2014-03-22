package controllers

import play.api.mvc._
import logic.game.Suit._
import logic.game.Number._
import views.Util.PlayerView
import logic.game.Pocket
import logic.room.Blind
import play.api.data._
import play.api.data.Forms._
import views.forms.{CallForm, BetForm}

object Application extends Controller {

  def index = Action {
    val player1 = new PlayerView("Name1",Some(Pocket(Ace of Hearts,Two of Clubs)),"Call",200,Blind.SMALL)
    val player2 = new PlayerView("Name2",None,"Bet",200,Blind.BIG)
    val templateRendered = views.html.index(List(Ace of Spades,Two of Clubs),player1::player2::Nil,betForm)
    Ok(templateRendered)
  }

  def startGame = Action{
    Ok(views.html.index(Nil,Nil,betForm))
  }

  def fold = Action{
    Ok(views.html.index(Nil,Nil,betForm))
  }

  def bet = Action{
    Ok(views.html.index(Nil,Nil,betForm))
  }

  def call = Action{
    Ok(views.html.index(Nil,Nil,betForm))
  }

  def check = Action{
    Ok(views.html.index(Nil,Nil,betForm))
  }

  val betForm:Form[BetForm] = Form(
    mapping(
      "amount" -> number
    )(BetForm.apply)(BetForm.unapply)
  )

  val callForm:Form[CallForm] = Form(
    mapping(
      "amount" -> number
    )(CallForm.apply)(CallForm.unapply)
  )

}
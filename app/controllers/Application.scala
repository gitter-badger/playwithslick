package controllers

import models.services.KnolService
import models.services.KnolServiceComponent
import play.api.mvc.Action
import play.api.mvc.Controller

class Application(knolDAL: KnolServiceComponent) extends Controller {

  def index = Action {
    Ok(views.html.index("ok"))
  }

  def showKnols = Action {
    val knols = knolDAL.getKnolList
    Ok(knols.toString)
  }

  def addKnol = TODO

  def deleteKnol = TODO

  def updateKnol = TODO
}

object Application extends Application(KnolService)
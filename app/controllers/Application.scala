package controllers

import play.api._
import play.api.mvc._
import models.domains.Knol
import models.dals.KnolDAL
import models.dals.KnolDALComponent

class Application(knolDAL: KnolDALComponent) extends Controller {

  def index = Action {
    val testKnol = Knol("sky", "sky@knoldus.com", "consultant")
    knolDAL.insertKnol(testKnol)
    val knolList = knolDAL.getKnols.toString
    Ok(knolList)
  }
}

object Application extends Application(KnolDAL)
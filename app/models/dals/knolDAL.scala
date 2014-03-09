package models.dals

import scala.slick.driver.JdbcProfile

import models.domains.DomainsComponent
import models.domains.Knol
import models.domains.Profile
import utils.Connection
import utils.SlickDBDriver

trait KnolDALComponent extends DomainsComponent with Profile {
  def insertKnol(knol: Knol): Int
  def updateKnol(knol: Knol): Int
  def deleteKnol(knolId: Int): Int
  def getKnols: List[Knol]

}
class KnolDAL(override val profile: JdbcProfile) extends KnolDALComponent with Connection {

  import profile.simple._

  def insertKnol(knol: Knol): Int = {
    dbObject.withSession { implicit session =>
      knolCustomInsert.insert(knol.name, knol.email, knol.designation)
    }

  }

  def updateKnol(knol: Knol): Int = {
    dbObject.withSession { implicit session =>
      knols.filter { k => k.id === knol.id }.update(knol)
    }
  }

  def deleteKnol(knolId: Int): Int = {
    dbObject.withSession { implicit session =>
      knols.filter { k => k.id === knolId }.delete
    }
  }

  def getKnols: List[Knol] = {
    dbObject.withSession { implicit session => knols.list }
  }
}

object KnolDAL extends KnolDAL(SlickDBDriver.getDriver)

package models.dals

import models.domains.Knol
import models.domains.DomainsComponent
import models.domains.Profile
import scala.slick.driver.JdbcProfile
import utils.SlickDBDriver
import play.api.db.DB
import play.api.Play.current

trait KnolDALComponent extends DomainsComponent with Profile {
  def insertKnol(knol: Knol): Int
  def updateKnol(knol: Knol): Int
  def deleteKnol(knolId: Int): Int
  def getKnols: List[Knol]

}
class KnolDAL(override val profile: JdbcProfile) extends KnolDALComponent {

  import profile.simple._

  lazy val dbObject = Database.forDataSource(DB.getDataSource())

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

package models.domains

import scala.slick.driver.JdbcProfile
import utils.SlickDBDriver
import play.api.Logger
import play.api.db.DB
import play.api.Play.current
import play.api.Play

class DomainsDDL(override val profile: JdbcProfile) extends DomainsComponent with Profile {
  import profile.simple._

  def create = {
    try {
      Database.forDataSource(DB.getDataSource()).withSession { implicit session =>
        (knols.ddl).create
        Logger.info("All tables have been created")

        // insert data for unit testing
        if (Play.current.mode.toString().toLowerCase() == "test") {
          knols.insert(Knol("test", "test@knoldus.com", "consultant"))
          Logger.info("Test data have been inserted")
        }
      }
    } catch {
      case exception: Exception => Logger.warn("Error in table creation" + exception.getMessage())
    }
  }

}

object DomainsDDL extends DomainsDDL(SlickDBDriver.getDriver)
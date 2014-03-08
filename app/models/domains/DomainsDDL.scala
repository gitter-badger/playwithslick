package models.domains

import scala.slick.driver.JdbcProfile
import utils.SlickDBDriver
import play.api.Logger
import play.api.db.DB
import play.api.Play.current

class DomainsDDL(override val profile: JdbcProfile) extends DomainsComponent with Profile {
  import profile.simple._

  def create = {
    try {
      Database.forDataSource(DB.getDataSource()).withSession { implicit session =>
        (knols.ddl ++ knolSessions.ddl).create
        Logger.info("All tables have been created")
      }
    } catch {
      case exception: Exception => Logger.warn("Error in table creation" + exception.getMessage())
    }
  }

}

object DomainsDDL extends DomainsDDL(SlickDBDriver.getDriver)
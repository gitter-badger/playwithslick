package utils

import scala.slick.driver.H2Driver
import scala.slick.driver.JdbcProfile
import scala.slick.driver.MySQLDriver
import scala.slick.driver.PostgresDriver
import play.api.Mode
import play.api.Logger
import play.api.Play

object SlickDBDriver {

  val TEST = "test"
  val DEV = "dev"
  val PROD = "prod"

  def getDriver: JdbcProfile = {
    Play.current.mode.toString().toLowerCase() match {
      case TEST => H2Driver
      case DEV => MySQLDriver
      case PROD => PostgresDriver
      case _ => PostgresDriver
    }
  }

}
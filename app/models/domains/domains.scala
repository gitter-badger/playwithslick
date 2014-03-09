package models.domains

import java.sql.Date

import scala.slick.driver.JdbcProfile

trait Profile {
  val profile: JdbcProfile
}

/**
 * describe all the database tables structure
 *
 */

trait DomainsComponent { this: Profile =>

  import profile.simple._

  class Knols(tag: Tag) extends Table[Knol](tag, "knol") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name", O.NotNull, O.DBType("VARCHAR(100)"))
    def email = column[String]("email", O.NotNull, O.DBType("VARCHAR(100)"))
    def designation = column[String]("designation", O.NotNull, O DBType ("VARCHAR(100)"))
    def * = (name, email, designation, id) <> (Knol.tupled, Knol.unapply)
  }
  val knols = TableQuery[Knols]

  def knolCustomInsert = knols.map(knol => (knol.name, knol.email, knol.designation)) returning knols.map { k => k.id }
  

}

case class KnolSession(topicName: String, date: Date, knolId: Int, id: Int = 0)
case class Knol(name: String, email: String, designation: String, id: Int = 0)
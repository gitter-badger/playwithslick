package utils

import play.api.Play.current
import play.api.db.DB
import models.domains.Profile

trait Connection extends Profile{
  
 import profile.simple._
 
  def dbObject = Database.forDataSource(DB.getDataSource())
  
}
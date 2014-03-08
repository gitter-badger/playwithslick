package models.dals

import org.specs2.mutable._
import play.api.test.Helpers.running
import play.api.test.FakeApplication
import models.domains.Knol
import utils.SlickDBDriver

class KnolDALTest extends Specification {
  val testKnol = Knol("sky", "sky@knoldus.com", "consultant", 1)

  "Knol data access layer " should {

    "knol ddl " in {
      running(FakeApplication()) {
        KnolDAL.insertKnol(testKnol) must be equalTo (1)
        KnolDAL.getKnols.length must be equalTo (1)

        KnolDAL.insertKnol(testKnol) must be equalTo (2)
        KnolDAL.getKnols.length must be equalTo (2)

        KnolDAL.updateKnol(testKnol.copy(name = "updatedname")) must be equalTo (1)
        KnolDAL.getKnols.length must be equalTo (2)
        
        KnolDAL.deleteKnol(1) must be equalTo (1)
        KnolDAL.getKnols.length must be equalTo (1)
      }
    }
  }
}
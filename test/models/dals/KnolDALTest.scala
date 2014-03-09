package models.dals

import org.specs2.mutable.Specification

import models.domains.Knol
import play.api.test.FakeApplication
import play.api.test.Helpers.running

class KnolDALTest extends Specification {

  val testKnol = Knol("sky", "sky@knoldus.com", "consultant")
  val updatedKnol = Knol("updatedtest", "test@knoldus.com", "consultant", 1)
  val fakeUpdatedKnol = Knol("updatedtest", "test@knoldus.com", "consultant", 2122) //have non existing id

  "Knol data access layer " should {

    "get knol list" in {
      running(FakeApplication()) {
        KnolDAL.getKnols.length must be equalTo (1)
      }
    }

    "add knol" in {
      running(FakeApplication()) {
        KnolDAL.insertKnol(testKnol) must be equalTo (2)
        KnolDAL.insertKnol(testKnol) must be equalTo (3)
      }
    }

    "update knol" in {
      running(FakeApplication()) {
        KnolDAL.updateKnol(updatedKnol) must be equalTo (1)
        KnolDAL.updateKnol(fakeUpdatedKnol) must be equalTo (0)
      }
    }

    "delete knol " in {
      running(FakeApplication()) {
        KnolDAL.deleteKnol(1) must be equalTo (1)
        KnolDAL.deleteKnol(1212) must be equalTo (0) // deleting non existing knol
      }
    }

  }
}

package models.services

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import models.dals.KnolDALComponent
import models.domains.Knol
import org.mockito.Mockito._

class KnolServiceTest extends Specification with Mockito {

  val mockedKnolDAL = mock[KnolDALComponent] // mocked database dependencies
  val knolService = new KnolService(mockedKnolDAL) //  mocked object  injected for service layer unit testing
  val knolList = List(Knol("test", "test@knoldus.com", "consultant", 1))
  val testKnol = Knol("sky", "sky@knoldus.com", "consultant")
  val updatedKnol = Knol("updatedtest", "test@knoldus.com", "consultant", 1)

  "KnolService " should {

    "get knol List " in {
      when(mockedKnolDAL.getKnols) thenReturn (knolList)
      knolService.getKnolList must be equalTo knolList
    }

    "add knol " in {
      when(mockedKnolDAL.insertKnol(testKnol)) thenReturn (2)
      knolService.addKnol(testKnol) must be equalTo (2)
    }
    "update knol " in {
      when(mockedKnolDAL.updateKnol(updatedKnol)) thenReturn (1)
      knolService.updateKnol(updatedKnol) must be equalTo (1)
    }
    "delete knol " in {
      val knolId = 122
      when(mockedKnolDAL.deleteKnol(knolId)) thenReturn (1)
      knolService.deleteKnol(knolId) must be equalTo (1)
    }

  }

}
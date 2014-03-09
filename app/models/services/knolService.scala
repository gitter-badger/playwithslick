package models.services

import models.dals.KnolDAL
import models.dals.KnolDALComponent
import models.domains.Knol

trait KnolServiceComponent {
  def addKnol(knol: Knol): Int
  def updateKnol(knol: Knol): Int
  def deleteKnol(knolId: Int): Int
  def getKnolList: List[Knol]
}

class KnolService(knolDAL: KnolDALComponent) extends KnolServiceComponent {

  def addKnol(knol: Knol): Int = knolDAL.insertKnol(knol)

  def updateKnol(knol: Knol): Int = knolDAL.updateKnol(knol)

  def deleteKnol(knolId: Int): Int = knolDAL.deleteKnol(knolId)

  def getKnolList: List[Knol] = knolDAL.getKnols

}

object KnolService extends KnolService(KnolDAL)
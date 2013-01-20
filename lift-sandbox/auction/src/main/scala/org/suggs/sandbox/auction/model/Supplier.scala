package org.suggs.sandbox.auction.model

import net.liftweb.mapper._


class Supplier extends LongKeyedMapper[Supplier] with IdPK {

  def getSingleton = Supplier

  object name extends MappedString(this, 150)

  object telephone extends MappedString(this, 30)

  object email extends MappedEmail(this, 200)

  object address extends MappedText(this)

  object openingHours extends MappedString(this, 255)

}

object Supplier extends Supplier with LongKeyedMetaMapper[Supplier] {
  override def dbTableName = "suppliers"
}
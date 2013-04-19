package org.suggs.sandbox.web.auction.model

import net.liftweb.mapper._

class Supplier extends LongKeyedMapper[Supplier]
with IdPK
with CreatedUpdated
with OneToMany[Long, Supplier] {

  def getSingleton = Supplier

  object name extends MappedString(this, 150)

  object telephone extends MappedString(this, 30)

  object email extends MappedEmail(this, 200)

  object address extends MappedText(this)

  object openingHours extends MappedString(this, 255)

  object tr extends MappedDouble(this)

}

object Supplier extends Supplier with LongKeyedMetaMapper[Supplier]
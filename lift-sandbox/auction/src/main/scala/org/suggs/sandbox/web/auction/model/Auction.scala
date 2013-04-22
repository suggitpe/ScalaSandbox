package org.suggs.sandbox.web.auction.model

import net.liftweb.mapper._

class Auction extends LongKeyedMapper[Auction]
with IdPK
with CreatedUpdated {

  def getSingleton = Auction

  object name extends MappedString(this, 150)

  object description extends MappedText(this)

  object endsAt extends MappedDateTime(this)

  object outboundOn extends MappedDateTime(this)

  object inboundOn extends MappedDateTime(this)

  object flyingFrom extends MappedString(this, 300)

  object isClosed extends MappedBoolean(this) {
    override def defaultValue = false
  }

  object startingAmount extends MappedDouble(this)

  object supplier extends MappedLongForeignKey(this, Supplier) {
    override def dbColumnName = "supplier_id"
  }

}

object Auction extends Auction with LongKeyedMetaMapper[Auction]
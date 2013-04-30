package org.suggs.sandbox.web.auction.model

import net.liftweb.mapper._

object Bid extends Bid with LongKeyedMetaMapper[Bid] {
  override def dbTableName = "bids"
}

class Bid extends LongKeyedMapper[Bid]
with IdPK
with CreatedUpdated {

  def getSingleton = Bid

  object amount extends MappedLong(this)

  object customer extends MappedLongForeignKey(this, Customer) {
    override def dbColumnName = "customer_id"
  }

  object auction extends MappedLongForeignKey(this, Auction) {
    override def dbColumnName = "auction_id"
  }

}

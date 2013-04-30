package org.suggs.sandbox.web.auction.model

import net.liftweb.mapper._

object OrderAuction extends OrderAuction with LongKeyedMetaMapper[OrderAuction] {
  override def dbTableName = "order_auction"
}

class OrderAuction extends LongKeyedMapper[OrderAuction]
with IdPK
with CreatedUpdated {

  def getSingleton = OrderAuction

  object name extends MappedString(this, 150)

  object order extends MappedLongForeignKey(this, Order) {
    override def dbColumnName = "order_id"
  }

}

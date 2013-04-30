package org.suggs.sandbox.web.auction.model

import net.liftweb.mapper._

object Order extends Order with LongKeyedMetaMapper[Order] {
  override def dbTableName = "orders"
}

class Order extends LongKeyedMapper[Order]
with IdPK
with CreatedUpdated
with OneToMany[Long, Order] {

  def getSingleton = Order

  object customer extends MappedLongForeignKey(this, Customer) {
    override def dbColumnName = "customer_id"
  }

  object order_auctions extends MappedOneToMany(OrderAuction, OrderAuction.order)
  with Owned[OrderAuction]

}

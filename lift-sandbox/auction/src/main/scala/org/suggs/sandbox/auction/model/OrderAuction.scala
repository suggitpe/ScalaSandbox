package org.suggs.sandbox.auction.model

import net.liftweb.mapper._

class OrderAuction extends LongKeyedMapper[OrderAuction] with IdPK {

  def getSingleton = OrderAuction

}
object OrderAuction extends OrderAuction with LongKeyedMetaMapper[OrderAuction]{
  override def dbTableName = "order_auction"
}
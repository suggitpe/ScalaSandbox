package org.suggs.sandbox.auction.model

import net.liftweb.mapper._

class Order extends LongKeyedMapper[Order] with IdPK {

  def getSingleton = Order

}

object Order extends Order with LongKeyedMetaMapper[Order] {
  override def dbTableName = "order"
}
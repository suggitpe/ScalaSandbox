package org.suggs.sandbox.auction.model

import net.liftweb.mapper._

class Customer extends LongKeyedMapper[Customer] with IdPK {

  def getSingleton = Customer

}

object Customer extends Customer with LongKeyedMetaMapper[Customer] {
  override def dbTableName = "customer"
}
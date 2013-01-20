package org.suggs.sandbox.auction.model

import net.liftweb.mapper._

class Bid extends LongKeyedMapper[Bid] with IdPK {

  def getSingleton = Bid

}

object Bid extends Bid with LongKeyedMetaMapper[Bid] {
  override def dbTableName = "bid"
}
package org.suggs.sandbox.auction.model

import net.liftweb.mapper._

class Auction extends LongKeyedMapper[Auction] with IdPK {

  def getSingleton = Auction

}
object Auction extends Auction with LongKeyedMetaMapper[Auction]
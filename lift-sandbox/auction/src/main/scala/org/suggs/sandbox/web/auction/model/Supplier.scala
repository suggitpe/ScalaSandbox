package org.suggs.sandbox.web.auction.model

import net.liftweb.mapper._
import scala.xml.NodeSeq
import net.liftweb.sitemap.Loc.LocGroup

object Supplier
  extends Supplier
  with LongKeyedMetaMapper[Supplier]
  with CRUDify[Long, Supplier] {
  override def dbTableName = "suppliers"

  override def pageWrapper(body: NodeSeq) =
    <lift:surround with="admin" at="content">
      {body}
    </lift:surround>

  override def calcPrefix = List("admin", _dbTableNameLC)

  override def displayName = "Supplier"

  override def showAllMenuLocParams = LocGroup("admin") :: Nil

  override def createMenuLocParams = LocGroup("admin") :: Nil
}

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

  object auctions extends MappedOneToMany(Auction, Auction.supplier,
    OrderBy(Auction.endsAt, Descending))
  with Owned[Auction]
  with Cascade[Auction]

}

package org.suggs.sandbox.web.auction.model

import net.liftweb.mapper._
import net.liftweb.common.{Full, Box}
import scala.xml.Node

object Customer extends Customer
with KeyedMetaMapper[Long, Customer]
with MetaMegaProtoUser[Customer] {

  override def dbTableName = "customers"

  override val basePath = "account" :: Nil

  override def skipEmailValidation = true

  override def screenWrap: Box[Node] =
    Full(
      <lift:surround with="default" at="content">
        <div id="box1" class="topbg">
          <lift:msgs showAll="true"/>
          <lift:bind/>
        </div>
        <lift:with-param name="sidebar">
          <lift:embed what="_light_baszket"/>
        </lift:with-param>
      </lift:surround>
    )
}

class Customer extends MegaProtoUser[Customer]
with CreatedUpdated {
  def getSingleton = Customer
}

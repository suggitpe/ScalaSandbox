package org.suggs.sandbox.web.auction.model

import net.liftweb.mapper._

object Customer extends Customer
with KeyedMetaMapper[Long, Customer]
with MetaMegaProtoUser[Customer] {

  override def dbTableName = "customers"

  override val basePath = "account" :: Nil

  override def skipEmailValidation = true
}

class Customer extends MegaProtoUser[Customer]
with CreatedUpdated{
  def getSingleton = Customer
}

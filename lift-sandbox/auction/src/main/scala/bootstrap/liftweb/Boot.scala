package bootstrap.liftweb

import net.liftweb._
import http.{LiftRules, NotFoundAsTemplate, ParsePath}
import sitemap.{SiteMap, Menu}
import net.liftweb.util.NamedPF
import net.liftweb._
import mapper.{Schemifier, DB, StandardDBVendor, DefaultConnectionIdentifier}
import util.Props
import org.suggs.sandbox.web.auction.model._
import net.liftweb.sitemap.Loc.LocGroup


class Boot {
  def boot {


    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor =
        new StandardDBVendor(
          Props.get("db.driver") openOr "org.h2.Driver",
          Props.get("db.url") openOr "jdbc:h2:auction.db;AUTO_SERVER=TRUE",
          Props.get("db.user"),
          Props.get("db.password"))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }
    Schemifier.schemify(true, Schemifier.infoF _, Auction, Bid, Customer, Order, OrderAuction, Supplier)

    // where to search snippet
    LiftRules.addToPackages("org.suggs.sandbox.web.auction")

    // build sitemap
    val entries = List(
      Menu("Home") / "index" >> LocGroup("public"),
      Menu("Admin") / "admin" / "index" >> LocGroup("admin"),
      Menu("Suppliers") / "admin" / "suppliers" >> LocGroup("admin") submenus (Supplier.menus: _*)
    ) ::: Customer.menus


    LiftRules.uriNotFound.prepend(NamedPF("404handler") {
      case (req, failure) => NotFoundAsTemplate(
        ParsePath(List("exceptions", "404"), "html", false, false))
    })

    LiftRules.setSiteMap(SiteMap(entries: _*))

    // set character encoding
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

  }

}
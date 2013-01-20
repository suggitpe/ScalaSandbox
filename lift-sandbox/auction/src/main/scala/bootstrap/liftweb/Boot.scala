package bootstrap.liftweb

import net.liftweb._
import sitemap.{SiteMap, Menu}
import util.{Props, NamedPF}
import net.liftweb.mapper._
import net.liftweb.http._
import org.suggs.sandbox.auction.model._

class Boot {
  def boot {


    setupDatabase

    // make http requests transactional
    S.addAround(DB.buildLoanWrapper())

    // where to search snippet
    LiftRules.addToPackages("org.suggs.sandbox.auction")

    Schemifier.schemify(true, Schemifier.infoF _, Auction, Bid, Customer, Order, OrderAuction, Supplier)

    // build sitemap
    val entries = (List(Menu("Home") / "index") :::

      Nil)

    LiftRules.uriNotFound.prepend(NamedPF("404handler") {
      case (req, failure) => NotFoundAsTemplate(
        ParsePath(List("exceptions", "404"), "html", false, false))
    })

    LiftRules.setSiteMap(SiteMap(entries: _*))

    // set character encoding
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))


  }

  def setupDatabase {

    if (!DB.jndiJdbcConnAvailable_?) {

      object DBVendor extends StandardDBVendor(
        Props.get("db.class").openOr("org.h2.Driver"),
        Props.get("db.url").openOr("jdbc:h2:database/auction_db;AUTO_SERVER=TRUE"),
        Props.get("db.user"),
        Props.get("db.pass"))

      DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)

      LiftRules.unloadHooks.append(
        () => DBVendor.closeAllConnections_!())
    }
  }
}
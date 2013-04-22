package bootstrap.liftweb

import net.liftweb._
import http.{LiftRules, NotFoundAsTemplate, ParsePath}
import sitemap.{SiteMap, Menu}
import net.liftweb.util.{Props, NamedPF}
import net.liftweb.mapper.Schemifier
import org.suggs.sandbox.web.auction.model.{Supplier, Auction}
import net.liftweb.db.{StandardDBVendor, DefaultConnectionIdentifier, DB}


class Boot {
  def boot {

    // where to search snippet
    LiftRules.addToPackages("org.suggs.sandbox.web.auction")

    // build sitemap
    val entries = (List(Menu("Home") / "index") :::

      Nil)

    Schemifier.schemify(true, Schemifier.infoF _, Auction, Supplier)

    if (!DB.jndiJdbcConnAvailable_?) {
      val databaseContext =
        new StandardDBVendor(
          Props.get("db.driver") openOr "org.h2.Driver",
          Props.get("db.url") openOr "jdbc:h2:database/temp;AUTO_SERVER=TRUE",
          Props.get("db.user"),
          Props.get("db.password"))

      DB.defineConnectionManager(DefaultConnectionIdentifier, databaseContext)

      LiftRules.unloadHooks.append(() => databaseContext.closeAllConnections_!())
    }

    LiftRules.uriNotFound.prepend(NamedPF("404handler") {
      case (req, failure) => NotFoundAsTemplate(
        ParsePath(List("exceptions", "404"), "html", false, false))
    })

    LiftRules.setSiteMap(SiteMap(entries: _*))

    // set character encoding
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

  }

}
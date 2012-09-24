package org.suggs.scalafundamentals.sandbox.extremestartup

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletHolder, ServletContextHandler}

/**
 * User: suggitpe
 * Date: 9/21/12
 * Time: 3:32 PM
 */
object DrunkMonkeyRunner {

  def main(args: Array[String]) = {
    val server = new Server(8080)
    val root = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)
    root.addServlet(new ServletHolder(new DrunkMonkey), "/*")
    server.start()
  }
}

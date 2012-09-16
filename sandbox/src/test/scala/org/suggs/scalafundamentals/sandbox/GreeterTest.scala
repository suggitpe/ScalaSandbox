package org.suggs.scalafundamentals.sandbox

import org.scalatest.FunSuite

/**
 * User: suggitpe
 * Date: 9/16/12
 * Time: 12:02 PM
 */
class GreeterTest extends FunSuite {

  test("should say hi to the correct person"){
    expect("Hello Bob"){
      new Greeter("Bob").sayHi()
    }

  }



}

package org.suggs.scalafundamentals.sandbox.recurse

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * User: suggitpe
 * Date: 9/24/12
 * Time: 7:18 AM
 */
@RunWith(classOf[JUnitRunner])
class CountChangeTest extends FunSuite{

  import CountChange.countChange

  test("manual") {
    assert(countChange(4,List(1,2)) === 3)
  }

  test("sorted CHF") {
    assert(countChange(300,List(5,10,20,50,100,200,500)) === 1022)
  }

  test("no pennies") {
    assert(countChange(301,List(5,10,20,50,100,200,500)) === 0)
  }

  test("unsorted CHF") {
    assert(countChange(300,List(500,5,50,100,20,200,10)) === 1022)
  }

}

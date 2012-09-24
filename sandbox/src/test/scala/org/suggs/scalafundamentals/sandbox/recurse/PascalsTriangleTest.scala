package org.suggs.scalafundamentals.sandbox.recurse

import org.scalatest.FunSuite

/**
 * User: suggitpe
 * Date: 9/21/12
 * Time: 7:32 AM
 */
class PascalsTriangleTest extends FunSuite {

  import PascalsTriangle.pascal

  test("col=0,row=2") {
    assert(pascal(0,2) === 1)
  }

  test("col=1,row=2") {
    assert(pascal(1,2) === 2)
  }

  test("col=1,row=3") {
    assert(pascal(1,3) === 3)
  }

  test("col=0,row=67"){
    assert(pascal(0,67) === 1)

  }

  test("col=7,row=2"){
    assert( pascal(7,2)=== 2)

  }

}

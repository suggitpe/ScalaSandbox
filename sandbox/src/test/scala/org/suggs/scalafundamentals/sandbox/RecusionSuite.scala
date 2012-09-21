package org.suggs.scalafundamentals.sandbox

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * User: suggitpe
 * Date: 9/20/12
 * Time: 5:41 PM
 */
@RunWith(classOf[JUnitRunner])
class RecusionSuite extends FunSuite {

  import Recusion.balance

  test("see '((()))' as balanced") {
    expect(true) {
      balance("((()))".toList)
    }
  }

  test("see '' as not balanced") {
    expect(true) {
      balance("".toList)
    }
  }
  test("see '(' as not balanced") {
    expect(false) {
      balance("(".toList)
    }
  }

  test("see ')' as not balanced") {
    expect(false) {
      balance(")".toList)
    }
  }

  test("see '()' as balanced") {
    expect(true) {
      balance("()".toList)
    }
  }

  test("see 'foo (bar) foo' as balanced") {
    expect(true) {
      balance("foo (bar) foo".toList)
    }
  }

  test("see ';-)' as not balanced") {
    expect(false) {
      balance(";-)".toList)
    }
  }

}

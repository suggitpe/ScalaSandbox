package org.suggs.scalafundamentals.sandbox.recurse

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * User: suggitpe
 * Date: 9/20/12
 * Time: 5:41 PM
 */
@RunWith(classOf[JUnitRunner])
class BalanceTest extends FunSuite {

  import Balance.balance

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
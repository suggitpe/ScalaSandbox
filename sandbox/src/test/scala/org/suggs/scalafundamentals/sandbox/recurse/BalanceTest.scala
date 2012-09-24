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

  test("'(if (zero? x) max (/ 1 x))' is balanced.") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }

  test("'I told him ...' is balanced.") {
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
  }

  test("':-)' is unbalanced.") {
    assert(!balance(":-)".toList))
  }

  test("Counting is not enough.") {
    assert(!balance("())(".toList))
  }

  test("see '())(()' as not balanced") {
    expect(false) {
      balance("())(()".toList)
    }
  }

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

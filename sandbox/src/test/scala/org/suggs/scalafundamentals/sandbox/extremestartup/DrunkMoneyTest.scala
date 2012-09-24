package org.suggs.scalafundamentals.sandbox.extremestartup

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

/**
 * User: suggitpe
 * Date: 9/21/12
 * Time: 3:57 PM
 */
@RunWith(classOf[JUnitRunner])
class DrunkMoneyTest extends FunSuite {

//  test("618 largest of 841, 93") {
//    expect("841") {
//      new DrunkMonkey().answerLargestOfSetQuestion("42c5ecc0: which of the following numbers is the largest: 93, 841")
//    }
//  }

  test("foo") {
    expect("20") {
      new DrunkMonkey().answerFibonacci("78390260: what is the 20th number in the Fibonacci sequence")
    }
  }
}

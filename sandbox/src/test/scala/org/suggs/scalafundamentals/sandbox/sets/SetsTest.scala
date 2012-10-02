package org.suggs.scalafundamentals.sandbox.sets

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class SetsTest extends FunSuite{


    /**
     * Link to the scaladoc - very clear and detailed tutorial of FunSuite
     *
     * http://doc.scalatest.org/1.8/index.html#org.scalatest.FunSuite
     *
     * Operators
     *  - test
     *  - ignore
     *  - pending
     */

    /**
     * Tests are written using the "test" operator and the "assert" method.
     */
    test("string take") {
      val message = "hello, world"
      assert(message.take(5) == "hello")
    }

    /**
     * For ScalaTest tests, there exists a special equality operator "===" that
     * can be used inside "assert". If the assertion fails, the two values will
     * be printed in the error message. Otherwise, when using "==", the test
     * error message will only say "assertion failed", without showing the values.
     *
     * Try it out! Change the values so that the assertion fails, and look at the
     * error message.
     */
    test("adding ints") {
      assert(1 + 2 === 3)
    }

    import Sets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  def same(s1: Set, s2: Set): Boolean =
  {
    diff(s1, s2) == diff(s2, s1)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      assert(setEquals(union(s1, s2), Set(1, 2)))
    }
  }

  test("intersection of {1,3,4,5,7,1000} and {1,2,3,4}") {
    val s = intersect(Set(1, 3, 4, 5, 7, 1000), Set(1, 2, 3, 4))
    assert(setEquals(s, Set(1, 3, 4)))
  }

  test("intersection of {1,2,3,4} and {-1000,0}") {
    val s = intersect(Set(1, 2, 3, 4), Set(-1000, 0))
    assert(setEquals(s, emptySet))
  }

  test("diff of {1,3,4,5,7,1000} and {1,2,3,4}") {
    val s = diff(Set(1, 3, 4, 5, 7, 1000), Set(1, 2, 3, 4))
    assert(setEquals(s, Set(5, 7, 1000)))
  }

  test("diff of {1,2,3,4} and {-1000,0}") {
    val s = diff(Set(1, 2, 3, 4), Set(-1000, 0))
    assert(setEquals(s, Set(1, 2, 3, 4)))
  }

  test("filter of {1,3,4,5,7,1000} for _ < 5") {
    val s = filter(Set(1, 3, 4, 5, 7, 1000), _ < 5)
    assert(setEquals(s, Set(1, 3, 4)))
  }

  test("forall: {1,3,4,5,7,1000}") {

  }

  // All elements in the set are strictly less than 5
  test("forall: {1,2,3,4}") {
    assert(forall(Set(1, 2, 3, 4), _ < 5))
  }

  // All elements in the set are strictly less than 1000
  test("forall: {-1000,0}") {
    assert(forall(Set(-1000, 0), _ < 1000))
  }

  // The set of all even numbers should contain only even numbers
  test("forall & filter: even") {
    assert(forall(Set(2, 4, 6, 8, 10, 22), _ % 2 == 0))
  }

  // 2 shouldn't exist in the given set
  test("exists: given {1,3,4,5,7,1000}") {
    assert(!exists(Set(1, 3, 4, 5, 7, 1000), _ == 2))
  }

  // 2 should exist in the given set
  test("exists: given {1,2,3,4}") {
    assert(exists(Set(1, 2, 3, 4), _ == 2))
  }

  test("two same sets are the same") {
    assert(setEquals(Set(1, 2, 3, 4), Set(1, 2, 3, 4)))
  }

  test("two different sets are not the same") {
    assert(!setEquals(Set(1, 2, 3, 4, 5), Set(1, 2, 3, 4)))
  }

  //The set of all even numbers should not contain odd element
  test("exists & filter: even") {}

  // The set of all even numbers and 3 should contain an odd element, namely 3
  test("exists & filter: even and 3") {}

  test("map: {1,3,4,5,7,1000}") {
    val s = map(Set(1, 3, 4, 5, 7, 1000), x => x * x)
    assert(setEquals(s, Set(1,9,16,25,49,1000000)))
  }

  test("forall & map: doubling numbers") {}

  test("exists should be implemented in terms of forall") {}

}


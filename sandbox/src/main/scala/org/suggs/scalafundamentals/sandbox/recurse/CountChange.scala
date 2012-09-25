package org.suggs.scalafundamentals.sandbox.recurse

/**
 * User: suggitpe
 * Date: 9/24/12
 * Time: 7:17 AM
 */
object CountChange {

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}

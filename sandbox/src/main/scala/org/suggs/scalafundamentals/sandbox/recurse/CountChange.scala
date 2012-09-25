package org.suggs.scalafundamentals.sandbox.recurse

/**
 * User: suggitpe
 * Date: 9/24/12
 * Time: 7:17 AM
 */
object CountChange {

  def countChange(money: Int, coins: List[Int]): Int = {

    def calcChange(moneyLeftOver: Int, coinsLeftOver: List[Int]): Int = {

      def foundValidCombination(): Boolean = moneyLeftOver == 0

      if (foundValidCombination) {
        1
      }
      else if (moneyLeftOver < 0 || coinsLeftOver.isEmpty) {
        0
      }
      else {
        calcChange(moneyLeftOver - coinsLeftOver.head, coinsLeftOver) + calcChange(moneyLeftOver, coinsLeftOver.tail)
      }
    }

    if (money == 0) {
      1
    }
    else {
      calcChange(money, coins.sortBy(_.intValue).reverse)
    }

  }

}

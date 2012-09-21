package org.suggs.scalafundamentals.sandbox

/**
 * User: suggitpe
 * Date: 9/20/12
 * Time: 5:41 PM
 */
object Recusion {

  def balance(chars: List[Char]): Boolean = {

    def checkBrackets(sublist: List[Char]): Boolean = {

      if (sublist.isEmpty)
        true
      else if (sublist.head == '(' && sublist.reverse.head == ')') {
        checkBrackets(sublist.tail.reverse.tail.reverse)
      }
      else
        false
    }

    checkBrackets(chars.filter(p => (p == '(' || p == ')')))
  }


}

package org.suggs.scalafundamentals.sandbox.recurse

/**
 * User: suggitpe
 * Date: 9/20/12
 * Time: 5:41 PM
 */
object Balance {

  def balance(chars: List[Char]): Boolean = {

    def getParenthesisValueOf(char: Char): Int = {
      if (char == '(')
        1
      else if (char == ')')
        -1
      else
        0
    }

    def evaluateList(sublist: List[Char], count: Int): Int = {
      if (!sublist.isEmpty) {
        if (count < 0) {
          Int.MaxValue
        }
        else
          evaluateList(sublist.tail, count + getParenthesisValueOf(sublist.head))
      } else {
        count
      }
    }

    evaluateList(chars, 0) == 0
  }


}

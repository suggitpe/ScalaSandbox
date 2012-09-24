package org.suggs.scalafundamentals.sandbox.recurse

/**
 * User: suggitpe
 * Date: 9/21/12
 * Time: 7:32 AM
 */
object PascalsTriangle {

  def pascal(column: Int, row: Int): Int = {
    if (row < 2 || column == 0 || row == column)
      1
    else
      pascal(column - 1, row - 1) + pascal(column, row - 1)
  }

}

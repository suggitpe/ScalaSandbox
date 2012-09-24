package org.suggs.scalafundamentals.sandbox.extremestartup

import org.scalatra.ScalatraServlet

/**
 * User: suggitpe
 * Date: 9/21/12
 * Time: 3:21 PM
 */
class DrunkMonkey extends ScalatraServlet {

  def answerLargestOfSetQuestion(question: String): String = {
    //println("answering largest in set question")
    var list = question.replaceAll(" ", "").split(":")(2).split(",").map(_.toInt)
    list.max.toString
  }

  def findPrimes(question: String): String = {
    var list = question.replaceAll(" ", "").split(":")(2).split(",").map(_.toInt)
    "hg"
  }

  def answerFibonacci(question: String): String = {

    def fibonacciOf(num: Int): Int = {
      if (num < 2) {
        num
      }
      else {
        fibonacciOf(num - 1) + fibonacciOf(num - 2)
      }
    }

    fibonacciOf(question.split(":")(1).split(" ")(4).dropRight(2).toInt).toString
  }

  def getfromConsole(): String = {
    println("Quick write an answer: ")
    print("--> ")
    Console.readLine()
  }

  def answer(question: String): String = {
    println(question)

    if (question.contains("which of the following numbers is the largest:")) {
      answerLargestOfSetQuestion(question)
    }
    else if (question.contains("Fibonacci sequence")) {
      answerFibonacci(question)
    }
    else if (question.contains("what is 1 to the power of ")) {
      "1"
    }
    //else if (question.contains("what is")) {
    //  answerAdditionQuestion(question)
    //}
    else {
      question.split(":")(1) match {
        case "who played James Bond in the film Dr No" => "Sean Connery"
        case _ => getfromConsole()
      }
    }
  }

  get("/") {
    params("q") match {
      case q: String =>
        System.out.println("A request has arrived [" + q + "]")
        answer(q)
      case _ => pass()
    }
  }


}

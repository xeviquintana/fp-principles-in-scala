package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  def pascal(c: Int, r: Int): Int =
    if c == 0 || c == r then 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  def balance(chars: List[Char]): Boolean =
    def mapChar(c: Char): Int =
      c match {
        case '(' => 1
        case ')' => -1
        case _ => 0
      }

    @tailrec
    def balance_accum(chars: List[Char], accum: Int): Boolean =
      if accum < 0 then false
      else if chars.isEmpty then accum == 0
      else balance_accum(chars.tail, accum + mapChar(chars.head))

    balance_accum(chars, 0)

  def countChange(money: Int, coins: List[Int]): Int =
    def countChangeAccum(money: Int, coins: List[Int], sum: Int): Int =
      if sum == money then 1
      else if sum > money || coins.isEmpty then 0
      else countChangeAccum(money, coins.tail, sum) + countChangeAccum(money, coins, sum + coins.head)

    if money == 0 || coins.isEmpty then 0
    else countChangeAccum(money, coins, 0)


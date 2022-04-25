package week2

import scala.annotation.tailrec
import scala.math.abs

@main def run(): Unit =
  val tolerance = 0.0001
  def isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x ) < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) =
    @tailrec
    def iterate(guess: Double): Double =
      val next = f(guess)
      println(next)
      if isCloseEnough(guess, next) then next
      else iterate(next)

    iterate(firstGuess)

  def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2)(1.0)
  println(sqrt(2))

  def averageDamp(f: Double => Double)(x: Double): Double =
    (x + f(x)) / 2

  def sqrt2(x: Double) = fixedPoint(averageDamp(y => x / y))(1.0)
  println(sqrt2(2))
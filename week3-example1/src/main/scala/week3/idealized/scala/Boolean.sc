//abstract class Boolean extends AnyVal:
//  def ifThenElse[T](t: => T, e: => T): T
//
//  def && (x: => Boolean): Boolean = ifThenElse(x, false)
//  def || (x: => Boolean): Boolean = ifThenElse(true, x)
//  def unary_! (x: => Boolean): Boolean = ifThenElse(false, true)
//
//  def == (x: Boolean): Boolean = ifThenElse(x, x.unary_!)
//  def != (x: Boolean): Boolean = ifThenElse(x.unary_!, x)
//
//  extension (x: Boolean):
//    def ==> (y: Boolean): Boolean = ifThenElse(y, true)
//
//
//object true extends Boolean:
//  def ifThenElse[T](t: => T, e: => T) = t
//
//object false extends Boolean:
//  def ifThenElse[T](t: => T, e: => T) = e

abstract class Nat:
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat
  def + (that: Nat): Nat
  def - (that: Nat): Nat
end Nat

object Zero extends Nat:
  def isZero: Boolean = true
  def predecessor: Nat = ???
  def successor: Nat = Succ(this)
  def +(that: Nat): Nat = that
  def -(that: Nat): Nat = if that.isZero then this else ???
  override def toString = "Zero"
end Zero

class Succ(n: Nat) extends Nat:
  def isZero: Boolean = false
  def predecessor: Nat = n
  def successor: Nat = Succ(this)
  def +(that: Nat): Nat = Succ(n + that)
  def -(that: Nat): Nat = if that.isZero then this else n - that.predecessor

  override def toString = s"Succ($n)"
end Succ

val two: Succ = Succ(Succ(Zero))
val five: Succ = Succ(Succ(Succ(Succ(Succ(Zero)))))
val three: Succ = Succ(Succ(Succ(Zero)))

five - two
three
(five - two).toString == three.toString // otherwise Nat != Succ so returns false

(three + two).toString == five.toString
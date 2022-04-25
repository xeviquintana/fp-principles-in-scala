import scala.annotation.tailrec

class Rational(x: Int, y: Int):
  require(y > 0, s"denominator must be positive, was $x/$y")
  def this(x: Int) = this(x, 1)

  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if b == 0 then a else gcd(b, a % b)

  def numer = x
  def denom = y

  def add(r: Rational) =
    Rational(
      numer * r.denom + r.numer * denom,
      denom * r.denom
    )

  def mul(r: Rational) =
    Rational(numer * r.numer, denom * r.denom)

  def neg = Rational(numer * -1, denom)

  def sub(r: Rational) = add(r.neg)

  override def toString = s"${numer / gcd(numer.abs, denom)}/${denom / gcd(numer.abs, denom)}"

end Rational

val x = Rational(1, 3)
val y = Rational(5, 7)
val z = Rational(3, 2)
x.add(y).mul(z)
x.sub(y).sub(z)

// Solution:
// - yes for small sizes of denominators and numerators and small number of operations
// if we had a lot of operations and bigger numbers, we might get an overflow error trying to fit
// larger integers than the type Integer is able to fit
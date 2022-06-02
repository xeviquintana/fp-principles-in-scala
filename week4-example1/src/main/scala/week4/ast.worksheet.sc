import scala.annotation.tailrec

trait Expr
case class Num(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Var(name: String) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr

def eval(e: Expr): Int = e.match
  case Num(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)

/**
 * Sum(Prod(2, Var("x")), Var("y")) should print "2 * x + y"
 *
 * Prod(Sum(2, Var("x")), Var("y")) should print "(2 + x) * y"
 */

val expr = Sum(Num(1), Num(1))
eval(expr)

def show(e: Expr): String = e match
  case Num(n) => n.toString
  case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
  case Var(x) => x
  case Prod(e: Sum, e2: Sum) => s"(${show(e)}) * (${show(e2)})"
  case Prod(e: Sum, e2: Expr) => s"(${show(e)}) * ${show(e2)}"
  case Prod(e: Expr, e2: Sum) => s"${show(e)} * (${show(e2)})"
  case Prod(e1, e2) => s"${show(e1)} * ${show(e2)}"

/**
 * More elegant solution:
 *
 * case Prod(e1, e2) => s"${showP(e1) * showP(e2)}"
 *
 * def showP(e: Expr): String = e match
 *  case e: Sum => s"(${show(e)})"
 *  case _ => s"${show(e)}"
 */

println(s"${show(expr)} = ${eval(expr)}")

val p = Sum(Prod(Num(2), Var("x")), Var("y"))
show(p)

val p2 = Prod(Sum(Num(2), Var("x")), Var("y"))
show(p2)


def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case y :: ys => if x < y then x :: xs else y :: insert(x, ys)
}

insert(2, 1 :: 3 :: Nil)
insert(1, Nil)
insert (3, 1 :: 2 :: Nil)
insert(1, 2 :: 3 :: Nil)
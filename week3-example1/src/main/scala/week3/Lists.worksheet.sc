import scala.annotation.tailrec

trait LIST[T]:
  def isEmpty: Boolean
  def head: T
  def tail: LIST[T]

class CONS[T](val head: T, val tail: LIST[T]) extends LIST[T]:
  override def isEmpty = false

class NIL[T] extends LIST[T]:
  override def isEmpty = true
  override def head = throw new NoSuchElementException("Nil.head")
  override def tail = throw new NoSuchElementException("Nil.tail")

@tailrec
def nth[T](xs: LIST[T], n: Int): T =
  if xs.isEmpty || n < 0 then
    throw new IndexOutOfBoundsException("nth: n is negative or greater than list size")
  else if n == 0 && !xs.isEmpty then
    xs.head
  else
    nth(xs.tail, n - 1)


val x = CONS(3, CONS(2, CONS(1, NIL())))
x.head
x.tail.head
x.tail.tail.head

nth(x, 0)
nth(x, 1)
nth(x, 2)
nth(x, 3)
nth(x, -1)

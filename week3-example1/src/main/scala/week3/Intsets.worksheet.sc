abstract class IntSet:
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(s: IntSet): IntSet

object Empty extends IntSet:
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)
  def union(s: IntSet): IntSet = s
  override def toString = ""

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet:
  def contains(x: Int): Boolean =
    if x < elem then left.contains(x)
    else if x > elem then right.contains(x)
    else true

  def incl(x: Int): IntSet =
    if x < elem then NonEmpty(elem, left.incl(x), right)
    else if x > elem then NonEmpty(elem, left, right.incl(x))
    else this

  def union(s: IntSet): IntSet =
    left.union(right).union(s).incl(elem)

  override def toString() = s"{${elem}}" + left + right

object IntSet:
  def apply(): IntSet = Empty
  def apply(x: Int): IntSet = NonEmpty(x, Empty, Empty)
    // should be def apply(x: Int): IntSet = Empty().incl(x)
  def apply(x: Int, y: Int): IntSet = NonEmpty(x, Empty, Empty).incl(y)
    // should be: def apply(x: Int, y: Int): IntSet = Empty().incl(x).incl(y)

IntSet()
IntSet(3)
IntSet(2, 5)
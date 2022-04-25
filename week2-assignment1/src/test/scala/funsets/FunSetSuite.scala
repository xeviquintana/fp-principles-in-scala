package funsets

import scala.math.abs

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */
  trait TestSets:
    val s1: FunSet = singletonSet(1)
    val s2: FunSet = singletonSet(2)
    val s3: FunSet = singletonSet(3)
    val isEven: Int => Boolean = (x: Int) => x % 2 == 0
    val isOdd: Int => Boolean = (x: Int) => x % 2 == 1

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton set one contains one") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s: FunSet = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("intersect contains no element") {
    new TestSets:
      val s: FunSet = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect 1 - empty")
      assert(!contains(s, 2), "Intersect 2 - empty")
  }

  test("intersect contains all elements in a set that are present in the other set, and the other way around") {
    new TestSets:
      val anotherOne: FunSet = singletonSet(1)
      val s: FunSet = intersect(s1, anotherOne)
      assert(contains(s, 1), "Intersect 1")
  }

  test("diff contains all elements in a set that are not in the other set") {
    new TestSets:
      val s: FunSet = diff(s1, s2)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
  }

  test("filter contains all elements in a set that satisfy the given predicate") {
    new TestSets:
      val s: FunSet = filter(s1, isEven)
      val t: FunSet = filter(s2, isEven)
      assert(!contains(s, 1), "Filter 1")
      assert(contains(t, 2), "Filter 2")
  }

  test("forall elements in a set satisfy the given predicate") {
    new TestSets:
      val s: FunSet = (x: Int) => -1 * abs(x) <= 0
      val p: (Int => Boolean) = (x: Int) => x * x >= 0
      assert(forall(s, p), "Forall negative numbers times themselves becomes a natural number")
  }

  test("forall out of bound elements do not count even they satisfy the given predicate") {
    new TestSets:
      val s: FunSet = singletonSet(9)
      assert(!forall(s, isEven), "Forall 9 is in bound but not even")
  }

  test("exists at least one element in a set that satisfies the given predicate") {
    new TestSets:
      val negativeNumbers: FunSet = (x: Int) => -1 * abs(x) <= 0
      val isZero: (Int => Boolean) = (x: Int) => x == 0
      assert(exists(negativeNumbers, isZero), "Exists 0 is negative number")
  }

  test("exist no element that satisfies the given predicate") {
    new TestSets:
      assert(!exists(isEven, isOdd), "Exists odd numbers are not even numbers")
  }

  test("map all elements to be their double") {
    new TestSets:
      val s: FunSet = (x: Int) => x > 0 && x < 5
      val d: Int => Int = (x: Int) => x * 2
      val res: FunSet = map(s, d)
      assert(FunSets.toString(res) == "{2,4,6,8}", "Map {1,2,3,4} to {2,4,6,8}")
  }


  import scala.concurrent.duration.*
  override val munitTimeout: FiniteDuration = 10.seconds

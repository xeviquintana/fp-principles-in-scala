# fp-principles-in-scala
Functional Programming Principles in Scala by École Polytechnique Fédérale de Lausanne - https://www.coursera.org/specializations/scala

## 2.1 High Order Functions

* The `sum` function uses linear recursion. Write a tail-recursive version by replacing the ???s.

```scala
def sum(f: Int => Int, a: Int, b: Int): Int =
  def loop(a: Int, acc: Int): Int =
    if ??? then ???
    else loop(???, ???)
  loop(???, ???)
```

> Solution in [Products worksheet](./src/main/scala/week2/products.worksheet.sc).

## 2.2 Currying

* Write a `product` function that calculates the product of the values of a function for the points on a given interval.
* Write `factorial` in terms of product.
* Can you write a more general function, which generalizes both `sum` and `product`?

> Solution in [products worksheet](./src/main/scala/week2/products.worksheet.sc)

## 2.3 Fixed points

* Write a square root function using `fixedPoint` and `averageDamp`.

> Solution in [main.scala](./src/main/scala/week2/main.scala)

## 2.5 Functions and data

* In your worksheet, add a method `neg` to class Rational that is used like this:

  `x.neg          // evaluates to -x`

* Add a method `sub` to subtract two rational numbers.
* With the values of `x`, `y`, `z` as given in the previous slide, what is the result of
  `x-y-z`?

> Solution in [rationals worksheet](./src/main/scala/week2/rationals.worksheet.sc)

## 2.6 Data Abstraction

* Modify the ``Rational class`` so that rational numbers are kept unsimplified internally, but the simplification is
  applied when numbers are converted to strings.

Do clients observe the same behavior when interacting with the rational class?
- yes
- no
- yes for small sizes of denominators and numerators and small number of operations

> Solution in [more-rationals worksheet](./src/main/scala/week2/more-rationals.worksheet.sc)

// high order functions
def product(f: Int => Int)(a: Int, b: Int): Int =
  if a > b then 1 else f(a) * product(f)(a + 1, b)

product(x => x * x)(1, 5)


def fact(n: Int): Int =
  product(x => x)(1, n)

fact(5)

def sum(f: Int => Int)(a: Int, b: Int): Int =
  if a > b then 0 else f(a) + sum(f)(a + 1, b)

sum(x => x * x)(1, 3)

// currying
def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
  def recur(a: Int): Int =
    if a > b then zero
    else combine(f(a), recur(a + 1))

  recur(a)

def sum2(f: Int => Int) = mapReduce(f, (x, y) => x + y, 0)

sum2(x => x)(1, 3)

def prod2(f: Int => Int) = mapReduce(f, (x, y) => x * y, 1)

prod2(x => x)(1, 5)

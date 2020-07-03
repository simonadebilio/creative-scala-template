package chapter10

object BuildingsLists {
  def ones(length: Int): List[Int] =
    length match {
      case 0 => Nil
      case n => 1 :: ones(n - 1)
    }

  def descending(length: Int): List[Int] =
    length match {
      case 0 => Nil
      case n => n :: descending(n - 1)
    }

  def ascending(num: Int): List[Int] =
    num match {
      case 0 => Nil
      case n => ascending(n - 1).appended(n)
    }

  def smartAscending(num: Int): List[Int] = {
    def iter(n: Int, counter: Int): List[Int] =
      n match {
        case 0 => Nil
        case m => counter :: iter(m - 1, counter + 1)
      }

    iter(num, 1)
  }

  def append[A](as: List[A], a: A): List[A] =
    as match {
      case Nil => a :: Nil
      case head :: tail => head :: append(tail, a)
    }

  def append2[A](as: List[A], a: A): List[A] =
    reverse(a :: reverse(as))

  def reverse[A](as: List[A]): List[A] =
    as match {
      case Nil => Nil
      case head :: tail => reverse(tail) ::: head :: Nil
    }

  // Create a method fill that accepts a natural number n and an element a of type A
  // and constructs a list of length n where all elements are a.
  def fill[A](n: Int, a: A): List[A] =
    n match {
      case 0 => Nil
      case m => a :: fill(m - 1, a)
    }

  // Write a method double that accepts a List[Int] and returns a list with each element doubled.
  def double(ints: List[Int]): List[Int] =
    ints match {
      case Nil => Nil
      case head :: tail => head * 2 :: double(tail)
    }

  // Write a method product that accepts a List[Int] and calculates the product of all the elements.
  def product(ints: List[Int]): Int =
    ints match {
      case Nil => 1
      case head :: tail => head * product(tail)
    }

  def main(args: Array[String]): Unit = {
    println(product(1 :: 2 :: 3 :: 4 :: Nil))
  }
}

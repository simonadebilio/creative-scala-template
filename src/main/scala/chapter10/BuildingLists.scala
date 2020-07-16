package chapter10

object BuildingLists {
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

  // Write a method contains that accepts a List[A] and an element of type A
  // and returns true if the list contains the element and false otherwise
  def contains[A](list: List[A], item: A): Boolean =
    list match {
      case Nil => false
      case head :: tail => head == item || contains(tail, item)
    }

  // Write a method first that accepts a List[A] and an element of type A and returns the first element
  // of the list if it is not empty and otherwise returns the element of type A passed as a parameter.
  def first[A](list: List[A], item: A): A =
    list match {
      case Nil => item
      case head :: tail => head
    }

  //Write a method reverse that accepts a List[A] and reverses the list.
  def reverse3[A](list: List[A]): List[A] =
    list match {
      case Nil => Nil
      case head :: tail => reverse(tail).appended(head)
    }

  def fill[A, B](list: List[A], b: B ): List[B] =
    list.map(_ => b)

  def main(args: Array[String]): Unit = {
    println(reverse3(List(4, 5, 6)))
  }
}

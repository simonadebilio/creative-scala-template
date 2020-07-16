package chapter10

object Ranges {

  //Write a method called ones that accepts an Int n and returns a List[Int]
  // with length n and every element is 1
  def ones(n: Int): List[Int] =
    (0 until n).toList.map(x => 1)


  //Write a method descending that accepts a natural number n and returns a List[Int]
  //containing the natural numbers from n to 1 or the empty list if n is zero
  def descending(n: Int): List[Int] =
    (n to 0 by -1).toList


  //Write a method ascending that accepts a natural number n and returns a List[Int]
  //containing the natural numbers from 1 to n or the empty list if n is zero.
  def ascending(n: Int): List[Int] =
    n match {
      case 0 => Nil
      case n => (1 until n).toList
    }


  //Write a method ascending that accepts a natural number n and returns a List[Int] containing the
  // natural numbers from 1 to n or the empty list if n is zero. Hint: use to.
  def ascending2(n: Int): List[Int] =
  n match {
    case 0 => Nil
    case n => (1 to n by 1).toList
  }


  //Write a method double that accepts a List[Int] and returns a list with each element doubled.
  def double(l: List[Int]): List[Int] =
    l.map(x => x * 2)


  def main(args: Array[String]): Unit = {
    println(ascending(5))
  }

}

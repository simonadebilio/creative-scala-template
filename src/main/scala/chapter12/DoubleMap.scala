package chapter12

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.turtle.Instruction._
import doodle.turtle._
import doodle.java2d._

object DoubleMap {
  // Using flatMap, write a method double that transforms a List to a List where every element appears twice.
  def double[A](d: List[A]): List[A] = {
    d.flatMap(x => List(x, x))
  }

  // Using flatMap , write a method nothing that transforms a List to the empty List.
  def nothing[A](n: List[A]): List[A] = {
    n.flatMap(x => Nil)
  }

  val y = Turtle.draw(List(
    forward(100),
    branch(turn(45.degrees), forward(100)),
    branch(turn(-45.degrees), forward(100)))
  )

  def myMap[A, B](la: List[A], f: A => B): List[B] = {
    la match {
      case ::(head, next) => f(head) :: myMap(next, f)
      case Nil => Nil
    }
  }

  //val lli = List(1, 2, 3) :: List(1, 2, 3) :: Nil : List[List[Int]]

  def myFlatten[A](la: List[List[A]]): List[A] = {
    la match {
      case ::(head, next) => head ::: myFlatten(next)
      case Nil => Nil
    }
  }

  def myFlatMap[A, B](la: List[A], f: A => List[B]): List[B] = {
    la match {
      case ::(head, next) => f(head) ::: myFlatMap(next, f)
      case Nil => Nil
    }
  }

  def main(args: Array[String]): Unit = {
    println(myFlatMap(List(1, 2, 3), (a: Int) => a :: Nil))
    println(myMap(List(1, 2, 3), (a: Int) => a :: Nil))
  }
}

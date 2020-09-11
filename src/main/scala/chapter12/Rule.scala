package chapter12

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.turtle.Instruction._
import doodle.turtle._
import doodle.java2d._

object Rule {
  val stepSize = 10

  def rule(i: Instruction): List[Instruction] =
    i match {
      case Forward(_) => {
        List(forward(stepSize), forward(stepSize))
      }
      case NoOp => {
        List(branch(turn(45.degrees), forward(stepSize), noop),
          branch(turn(-45.degrees), forward(stepSize), noop))
      }
      case other => {
        List(other)
      }
    }

  // This method should apply `rule` to rewrite every instruction in `instructions`,
  // except for branches which you’ll need to handle specially.
  // If you encounter a branch you should rewrite all the instructions inside the branch
  // but leave the branch alone.
  // Note: You’ll need to pass a `List[Instruction]` to `branch`,
  // while `branch` itself accepts zero or more instructions (so-called _varargs_).
  // To convert the `List[Instruction]` into a form that `branch` will accept, follow the
  // parameter with `:_*`
  def rewrite(instructions: List[Instruction],
              rule: Instruction => List[Instruction]): List[Instruction] = {
    instructions match {
      case ::(head, next) =>
        head match {
          case Branch(is) => branch(rewrite(is, rule): _*) :: rewrite(next, rule)
          case other => rule(other) ::: rewrite(next, rule)
        }
      case Nil => Nil
    }
  }

  def rewrite2(instructions: List[Instruction],
               rule: Instruction => List[Instruction]): List[Instruction] =
    instructions.flatMap { i =>
      i match {
        case Branch(i) =>
          List(branch(rewrite2(i, rule): _*))
        case other =>
          rule(other)
      }
    }

  // We’re now ready to create a complete L-system. Using `rewrite` from above, create a method `iterate`.
  // This should recursively apply `rule` to `seed` for `steps` iterations.
  def iterate(steps: Int,
              seed: List[Instruction],
              rule: Instruction => List[Instruction]): List[Instruction] = {
    steps match {
      case 0 => seed
      //      case 1 => rewrite(seed, rule)
      //      case 2 => rewrite(rewrite(seed, rule), rule)
      case n => rewrite(iterate(n - 1, seed, rule), rule)
    }
  }

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    (a: A) => g(f(a))

  def main(args: Array[String]): Unit = {
    val base = List(
      forward(stepSize),
      branch(turn(45.degrees), forward(stepSize), noop),
      branch(turn(-45.degrees), forward(stepSize), noop)
    )
    //
    //    (Turtle.draw(base) beside Turtle.draw(rewrite(base, rule))).draw()

    //    (Turtle.draw(iterate(4, base, rule)) beside Turtle.draw(rewrite(rewrite(base, rule), rule))).draw()
    (Turtle.draw(iterate(0, base, rule)) beside
      Turtle.draw(iterate(1, base, rule)) beside
      Turtle.draw(iterate(2, base, rule)) beside
      Turtle.draw(iterate(4, base, rule)))
      .draw()


    val plusOne: Int => Int = (n: Int) => n + 1
    val timesThree: Int => Int = (m: Int) => m * 3
    val print: Int => Unit = (i: Int) => println(i)
    val doEverything: Int => Unit = andThen(andThen(plusOne, timesThree), print)
    val doEverythingAgain: Int => Unit = andThen(andThen(timesThree, plusOne), print)

    doEverythingAgain(2)

  }

}

package chapter07

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object AlternatingImages {
  val circle = Image.circle(30)
  val square = Image.rectangle(30, 30)

  def seq(count: Int): Image =
    count match {
      case 0 => Image.empty
      case n =>
        if (n % 2 == 0) {
          circle.beside(seq(n - 1))
        }
        else {
          square.beside(seq(n - 1))
        }
    }
}

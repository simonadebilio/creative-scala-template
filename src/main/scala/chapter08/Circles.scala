package chapter08

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object Circles {
  def concentric(count: Int, size: Int): Image =
    count match {
      case 0 => Image.empty
      case n => Image
        .circle(size)
        .strokeWidth(2)
        .strokeColor(Color.blue)
        .under(concentric(n - 1, size - 20))
    }

  def fading(count: Int, size: Int, fade: Double): Image =
    count match {
      case 0 => Image.empty
      case n => Image
        .circle(size)
        .strokeWidth(5)
        .strokeColor(Color.hsl(10.degrees, 1, fade))
        .under(fading(n - 1, size - 30, fade - 0.05))
    }

  def colored(count: Int, size: Int, stroke: Int): Image =
    count match {
      case 0 => Image.empty
      case n => Image
        .circle(size)
        .strokeWidth(2)
        .strokeColor(Color.hsl(stroke.degrees, 1, 0.5))
        .under(colored(n - 1, size - 20, stroke + 15))
    }
}

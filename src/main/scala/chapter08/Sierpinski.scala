package chapter08

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object Sierpinski {
  def sierpinski(count: Int): Image = {
    val triangle = Image
      .triangle(20, 30)
      .strokeWidth(3)
      .strokeColor(Color.red)

    count match {
      case 0 => (triangle beside triangle) below triangle
      case n =>
        val unit = sierpinski(n - 1)
        (unit beside unit) below unit
    }
  }


}

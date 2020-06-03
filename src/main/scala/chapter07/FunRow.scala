package chapter07

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object FunRow {
  def funRow(count: Int): Image =
    count match {
      case 0 => Image.empty
      case n => Image.rectangle(100 * n, 80 * n)
        .strokeWidth(4)
        .fillColor(Color.red.spin((5 * n).degrees))
        .beside(funRow(n - 1))
    }
}

package chapter08

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object GradientBoxes {
  def gradientBoxes(count: Int, inside: Double, stroke: Int): Image =
    count match {
      case 0 => Image.empty
      case n =>
        Image
          .square(100)
          .fillColor(Color.red.spin((inside).degrees))
          .strokeWidth(4)
          .strokeColor(Color.red.spin((stroke).degrees))
            .beside(gradientBoxes(n - 1, inside + 20, stroke + 10))
    }
}

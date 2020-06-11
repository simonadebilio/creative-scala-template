package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object ParametricCircle {
  def parametricCircle(angle: Angle): Point =
    Point.polar(200, angle)

  def sample(samples: Int): Image = {
    // Angle.one is one complete turn. I.e. 360 degrees
    val step = Angle.one / samples
    val dot = Image
      .triangle(10, 10)
      .fillColor(Color.limeGreen)
      .strokeColor(Color.lawngreen)

    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n =>
          dot.at(parametricCircle(angle)).on(loop(n - 1))
      }
    }

    loop(samples)
  }
}

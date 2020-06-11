package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object ParametricCurve {
  def sample(samples: Int, dot: Image, curve: Angle => Point): Image = {
    val step = Angle.one / samples

    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n =>
          dot.at(curve(angle)).on(loop(n - 1))
      }
    }

    loop(samples)
  }

  def spiral(angle: Angle): Point =
    Point.polar((angle * 2).toDegrees, angle)

  def parametricSpiral(samples: Int): Image = {
    val dot = Image
      .triangle(10, 10)
      .fillColor(Color.limeGreen)
      .strokeColor(Color.lawngreen)

    sample(samples, dot, spiral)
  }
}

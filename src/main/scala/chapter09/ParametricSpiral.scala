package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax.core._
import Sample.sample

object ParametricSpiral {
  def spiral(angle: Angle, ratio: Double, rotation: Double): Point =
    Point.polar((angle * ratio).toDegrees, angle + rotation.degrees)

  def parametricSpiral(samples: Int, ratio: Double, rotation: Double): Image = {
    val dot = Image
      .triangle(10, 10)
      .fillColor(Color.limeGreen)
      .strokeColor(Color.lawngreen)

    sample(samples, dot, spiral(_, ratio, rotation))
  }

  def allSpirals(numberOfSpirals: Int): Image = {
    val step = Angle.one / numberOfSpirals

    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n =>
          parametricSpiral(samples = 200, ratio = 0.5, rotation = angle.toDegrees).on(loop(n - 1))
      }
    }

    loop(numberOfSpirals)
  }
}

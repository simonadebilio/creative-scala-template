package chapter09

import doodle.core.{Angle, Point}
import doodle.image.Image
import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import Sample.sample

object GrowingCircle {
  def sample(samples: Int, curve: Angle => Image): Image = {
    val step = Angle.one / samples

    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n =>
          curve(angle).on(loop(n - 1))
      }
    }

    loop(samples)
  }

  val parametricCircle: Angle => Point =
    (angle: Angle) => Point(1.0, angle)

  def scale(factor: Double): Point => Point =
    (point: Point) => Point(point.r * factor, point.angle)

  val growingDot: Point => Image =
    (pt: Point) => Image.circle(pt.angle.toTurns * 20).at(pt)

  val growingCircle = parametricCircle
    .andThen(scale(100))
    .andThen(growingDot)

  def main(args: Array[String]): Unit = {
    sample(100, growingCircle).draw()
  }
}

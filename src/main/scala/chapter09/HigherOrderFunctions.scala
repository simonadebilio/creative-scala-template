package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import Sample.sample

object HigherOrderFunctions {
  val parametricCircle: Angle => Point =
    (angle: Angle) => Point(1.0, angle)

  def scale(factor: Double): Point => Point =
    (point: Point) => Point(point.r * factor, point.angle)

  val parametricSpiral: Angle => Point =
    (angle: Angle) => Point(Math.exp(angle.toTurns - 1), angle)

  val spiral100 = parametricSpiral.andThen(scale(100))
  val dot = Image.circle(5).fillColor(Color.darkRed).strokeColor(Color.darkRed)


  def main(args: Array[String]): Unit = {
    sample(400, dot, spiral100).draw()
  }
}

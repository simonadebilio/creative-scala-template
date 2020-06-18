package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import Sample.sample

object Lissajous {
  def lissajous(a: Int, b: Int, offset: Angle): Angle => Point =
    (angle: Angle) =>
      Point(100 * ((angle * a) + offset).sin, 100 * (angle * b).sin)

  val dot = Image.square(10).fillColor(Color.deepPink)

  def main(args: Array[String]): Unit = {
    sample(500, dot, lissajous(10, 5, Angle(90.toDegrees))).draw()
  }
}

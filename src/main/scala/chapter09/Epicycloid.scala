package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import Sample.sample

object Epicycloid {
  def epicycloid(a: Int, b: Int, c: Int): Angle => Point =
    (angle: Angle) =>
      (Point(75, angle * a).toVec + Point(32, angle * b).toVec + Point(15, angle * c).toVec).toPoint

  val dot = Image.circle(5).fillColor(Color.deepSkyBlue)

  def main(args: Array[String]): Unit = {
    sample(900, dot, epicycloid(5, 23, 8)).draw()
  }
}

package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import Sample.sample

object Rose {
  def rose(k: Int): Angle => Point =
    (angle: Angle) => Point((angle * k).cos * 200, angle)

  val dot = Image.circle(10).fillColor(Color.mediumPurple)

  def main(args: Array[String]): Unit = {
    sample(500, dot, rose(5)).draw()
  }
}


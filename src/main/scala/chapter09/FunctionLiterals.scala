package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object FunctionLiterals {
  val square = (x: Int) => x * x

  val colorz = (c: Double) => Image
    .circle(100)
    .fillColor(Color.hsl((c + 15).degrees, 0.5, 0.5))

  val spinBy15Deg = (c: Color) => c.spin(15.degrees)

  val rotatedImages = (f: Image) => f
    .beside(f.rotate(90.degrees))
    .beside(f.rotate(180.degrees))
    .beside(f.rotate(270.degrees))
    .beside(f.rotate(360.degrees))

  val roseFn = (ngl: Angle) => Point
    .cartesian((ngl * 7)
      .cos * ngl.cos, (ngl * 7)
      .cos * ngl.sin)
}

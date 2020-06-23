package chapter09

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import Sample.sample

object ConcentricImages {
  def concentricCircles(count: Int, size: Int): Image =
    count match {
      case 0 => Image.empty
      case n => Image.circle(size).on(concentricCircles(n - 1, size + 5))
    }

  def concentricShapes(count: Int, singleShape: Int => Image): Image =
    count match {
      case 0 => Image.empty
      case n => singleShape(n).on(concentricShapes(n - 1, singleShape))
    }

  def colored(shape: Int => Image, color: Int => Color): Int => Image =
    (n: Int) => shape(n).fillColor(color(n)).strokeColor(Color.white).strokeWidth(2.0)

  def concentricShapes2(count: Int, coloredShape: Int => Image): Image =
    count match {
      case 0 => coloredShape(1).fillColor(Color.white)
      case n => coloredShape(n).under(concentricShapes2(n - 1, coloredShape))
    }

  def main(args: Array[String]): Unit = {
    val circle: Int => Image = (n: Int) => Image.circle(n * 30)
    val square: Int => Image = (n: Int) => Image.square(n * 30)
    val triangle: Int => Image = (n: Int) => Image.triangle(n * 30, n * 30)
    val rainbow: Int => Color = (n: Int) => Color.hsl((n * 30).degrees, 0.7, 0.5)
    val shade: Int => Color = (n: Int) => Color.blue.darken((n * 0.04).normalized)

    concentricShapes2(10, colored(circle, rainbow))
      .beside(concentricShapes2(10, colored(triangle, shade))
        .beside(concentricShapes2(10, colored(square, rainbow))))
      .draw()
  }
}

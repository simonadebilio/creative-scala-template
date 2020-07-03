package chapter10

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.core.PathElement._

object Paths {
  val triangle =
    List(
      lineTo(Point(50, 100)),
      lineTo(Point(100, 0)),
      lineTo(Point(0, 0))
    )

  val curve =
    List(curveTo(Point(50, 100), Point(100, 100), Point(150, 0)))

  def style(image: Image): Image =
    image.strokeWidth(6.0)
      .strokeColor(Color.royalBlue)
      .fillColor(Color.skyBlue)

  val openPaths =
    style(Image.openPath(triangle).beside(Image.openPath(curve)))

  val closedPaths =
    style(Image.closedPath(triangle).beside(Image.closedPath(curve)))

  val paths = openPaths.above(closedPaths)

  def main(args: Array[String]): Unit = {
    paths.draw()
  }

}

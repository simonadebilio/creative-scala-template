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
    image.strokeWidth(0.2)
      .strokeColor(Color.royalBlue)

  //      .fillColor(Color.skyBlue)

  val openPaths =
    style(Image.openPath(triangle).beside(Image.openPath(curve)))

  val closedPaths =
    style(Image.closedPath(triangle).beside(Image.closedPath(curve)))

  val paths = openPaths.above(closedPaths)

  // Create paths to define a triangle, square, and pentagon.
  // Hint: you might find it easier to use polar coordinates to define the polygons.
  def polygon(sides: Int): Image = {
    val angle = 360.degrees / sides

    def buildPolygon(sides: Int): List[PathElement] =
      sides match {
        case 0 => Nil
        case n => lineTo(Point(100, angle * n)) :: buildPolygon(n - 1)
      }

    val polygon = moveTo(Point(100, 0.degrees)) :: buildPolygon(sides)
    Image.closedPath(polygon)
  }

  def printPolygon(polygon: List[PathElement]): String = {
    polygon match {
      case Nil => ""
      case hd :: tl => hd match {
        case MoveTo(to) => s"MoveTo(${to.r}, ${to.angle.toDegrees % 360}) " + printPolygon(tl)
        case LineTo(to) => s"LineTo(${to.r}, ${to.angle.toDegrees % 360}) " + printPolygon(tl)
        case _ => "??? " + printPolygon(tl)
      }
    }
  }

  def polygon2(sides: Int, size: Int, initialRotation: Angle): Image = {
    val angle = 360.degrees / sides

    val polygon = moveTo(Point(size, 0.degrees + initialRotation)) :: (0 to sides)
      .map(x => angle * x + initialRotation)
      .map(a => Point(size, a))
      .map(p => lineTo(p))
      .toList

    Image.closedPath(polygon)
  }

  def main(args: Array[String]): Unit = {
    polygon2(8, 100, 30.degrees).draw()
  }

}

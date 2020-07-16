package chapter10

import chapter09.Sample
import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import Point._
import PathElement._

object Stars {
  def star(points: Int, revolutions: Int, radius: Double): Image = {
    val angle = 360.degrees * revolutions / points

    def buildStar(points: Int): List[PathElement] =
      points match {
        case 0 => Nil
        case n => lineTo(Point(radius, angle * n)) :: buildStar(n - 1)
      }

    val star = moveTo(Point(radius, 0.degrees)) :: buildStar(points)
    Image.closedPath(star)
  }

  def star2(points: Int, revolutions: Int, radius: Double): Image = {
    val angle = 360 * revolutions / points

    val star = moveTo(Point(radius, 0.degrees)) :: (0 until 360 * revolutions by angle)
      .map(n => lineTo(Point(radius, n.degrees)))
      .toList

    Image.closedPath(star)
  }

  def stars(n: Int): List[Image] = {
    n match {
      case 0 => Nil
      case n => star(11, n, 100) :: stars(n - 1)
    }
  }

  def allBeside(images: List[Image]): Image = {
    images match {
      case Nil => Image.empty
      case hd :: tl => hd beside (allBeside(tl))
    }

  }

  def main(args: Array[String]): Unit = {
    allBeside(stars(2)).draw()
  }
}

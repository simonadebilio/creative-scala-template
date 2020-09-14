package chapter13

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._
import RandomColor._
import GradientOfParticles._
import doodle.core.PathElement._

object ScatterPlots {
  def makePoint(x: Random[Double], y: Random[Double]): Random[Point] = {
    for {
      theX <- x
      theY <- y
    } yield Point.cartesian(theX, theY)
  }

  val normal = Random.normal(-30, 100)
  val point = makePoint(normal, normal)

  def randomPoints(numberOfPoints: Int): List[Random[Point]] = {
    (1 until numberOfPoints).toList.map(n => point)
  }

  def render(point: Point): Image = {
    val color = Random.int.map(i => Color.hsl(i.degrees, 0.5, 0.5))

    Image.circle(12).strokeColor(color.run).at(point)
  }

  val canvas: Image =
    Image
      .closedPath(List(lineTo(Point(0, 1000)),
        lineTo(Point(1000, 1000)),
        lineTo(Point(1000, 0)),
        lineTo(Point(0, 0))
      ))
      .fillColor(Color.coral.alpha(0.1.normalized))

  def toRandomImages(randomPoints: List[Random[Point]]): List[Random[Image]] =
    randomPoints.map(randomPoint => randomPoint.map(point => render(point)))

  def allOn(toRandomImages: List[Random[Image]]): Random[Image] =
    toRandomImages match {
      case Nil => Random.always(Image.empty)
      case img :: imgs =>
        for {
          i <- img
          is <- allOn(imgs)
        } yield (i on is)

//              img.flatMap(i => allOn(imgs).map(is => (i on is)))
    }


  def main(args: Array[String]): Unit = {
    allOn(toRandomImages(randomPoints(40))).run.draw()
  }

}

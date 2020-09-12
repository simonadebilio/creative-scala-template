package chapter13

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._

object RandomCircles {

  //  Write a method that accepts a radius and a Random[Color],
  //  and produces a circle of the given radius and filled with the given random color.
  //  It should have the signature:
  def randomCircle(r: Double, color: Random[Color]): Random[Image] =
    color.map(fill => Image.circle(r).fillColor(fill))

  val randomAngle: Random[Angle] =
  Random.double.map(x => x.turns)

  def randomColor(s: Double, l: Double): Random[Color] =
  randomAngle.map(hue => Color.hsl(hue, s, l))

  val randomPastel = randomColor(0.7, 0.7)

  def randomConcentricCircles(count: Int, size: Int): Random[Image] =
    count match {
      case 0 => Random.always(Image.empty)
      case n =>
        randomCircle(size, randomPastel).flatMap { circle =>
          randomConcentricCircles(n - 1, size + 35).map { circles =>
            circle.on(circles)
          }
        }
    }

  // randomConcentricCircles lavora sui tipi: Random[Inage] flatMap (Random[Image] map (Image => Image))

  def main(args: Array[String]): Unit = {
    // (randomConcentricCircles(10, 300).run).draw()
    (randomCircle(100, randomColor(0.5, 0.5)).run).draw()
  }

}

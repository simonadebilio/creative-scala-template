package chapter13

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._

object RandomColor {

  val randomAngle: Random[Angle] =
    Random.double.map(x => x.turns)

  //  Given randomAngle above, create a method that accepts saturation and lightness
  //    and generates a random color.Your method should have the signature:
  def randomColor(s: Normalized, l: Normalized): Random[Color] = {
    randomAngle.map(a => Color.hsl(a, s.get, l.get))
  }

  def randomColor2(s: Double, l: Double): Random[Color] = {
    randomAngle.map(x => Color.hsl(x, s, l))
  }

  def randomColor3(s: Double, l: Double): Random[Color] = {
    Random.int.map(x => Color.hsl(x.degrees, s, l))
  }


  def main(args: Array[String]): Unit = {
    println(randomColor(0.5.normalized, 0.5.normalized).run)
    println(randomColor2(0.5, 0.5).run)
    println(randomColor3(0.5, 0.5).run)

    Image.circle(300).fillColor(randomColor(0.5.normalized, 0.5.normalized).run)
      .beside(Image.circle(300).fillColor(randomColor2(0.5, 0.5).run))
      .beside(Image.circle(300).fillColor(randomColor3(0.5, 0.5).run)).draw()
  }
}
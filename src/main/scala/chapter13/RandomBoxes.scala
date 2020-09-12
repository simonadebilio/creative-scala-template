package chapter13

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._
import RandomCircles._

object RandomBoxes {

  def rowOfBoxes(count: Int): Image =
    count match {
      case 0 => Image.rectangle(20, 20)
      case n => Image.rectangle(20, 20).beside(rowOfBoxes(n - 1))
    }

  def randomBox(l: Int, color: Random[Color]): Random[Image] = color.map(c => Image.square(l)
    .fillColor(c))

  def randomlyColoredBoxes(count: Int, size: Int): Random[Image] = {
    count match {
      case 0 => Random.always(Image.empty)
      case n => randomBox(size, randomPastel)
        .flatMap(box => randomlyColoredBoxes(n - 1, size)
          .map(boxes => box beside boxes))
    }
  }

  def randomChessboard(count: Int, size: Int): Random[Image] = {
    val base: Random[Image] = randomBox(size, randomPastel)
      .flatMap(box => randomBox(size, randomPastel)
        .map(boxes => (box beside boxes) above (boxes beside box)))

    count match {
      case 0 => base
      case n =>
        val unit = randomChessboard(n - 1, size)

        unit.flatMap(b => unit.map(u => (b above u) beside (b above u)))
    }
  }

  def nextColor(color: Color): Random[Color] = {
    val spin = Random.normal(15.0, 10.0)
    spin.map{ s => color.spin(s.degrees) }
  }

  def coloredRectangle(color: Color, size: Int): Image =
    Image.rectangle(size, size)
      .strokeWidth(5.0)
      .strokeColor(color.spin(30.degrees))
      .fillColor(color)

  def randomGradientBoxes(count: Int, color: Color): Random[Image] =
    count match {
      case 0 => Random.always(Image.empty)
      case n =>
        val box = coloredRectangle(color, 20)
        val boxes = nextColor(color).flatMap{ c => randomGradientBoxes(n-1, c) }
        boxes.map{ b => box beside b }
    }


  def main(args: Array[String]): Unit = {
    (randomGradientBoxes(5, Color.red).run).draw()
  }

}
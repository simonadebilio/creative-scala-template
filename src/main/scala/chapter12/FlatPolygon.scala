package chapter12

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.turtle.Instruction._
import doodle.turtle._
import doodle.java2d._

object FlatPolygon {
  def polygon(sides: Int, sideLength: Double): Image = {
    val rotation = Angle.one / sides

    def buildPolygon(sides: Int, sideLength: Double, rotation: Angle): List[Instruction] = {
      (1 to sides).toList.flatMap(s => List(forward(sideLength), turn(rotation)))
    }

    Turtle.draw(buildPolygon(sides, sideLength, rotation))
  }

  def squareSpiral(steps: Int, distance: Double, angle: Angle, increment: Double): Image = {
    val spiral = (1 to steps).toList.flatMap(s => List(forward(s + distance * increment), turn(angle)))

    Turtle.draw(spiral)
  }


  def main(args: Array[String]): Unit = {
    squareSpiral(100, 10.0, 60.degrees, 3.0).draw()
  }
}

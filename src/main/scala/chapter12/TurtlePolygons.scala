package chapter12

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.turtle.Instruction._
import doodle.turtle._
import doodle.java2d._
import doodle.core.Point._

object TurtlePolygons {

  // In the previous chapter we wrote a method to create a polygon. Reimplement this method using turtle graphics instead
  def polygon(sides: Int, sideLength: Double): Image = {
    val rotation = 180.degrees * (sides - 2) / sides
    println(s"rotation: ${rotation.toDegrees}")

    def buildPolygon(sides: Int): List[Instruction] = {
      sides match {
        case 0 => Nil
        case n => forward(sideLength) :: turn(rotation) :: buildPolygon(n - 1)
      }
    }

    Turtle.draw(buildPolygon(sides))
  }

  // Write a method to create square spirals using turtle graphics.
  // This task requires a bit more design work than we usually ask of you. You’ll have to work out how the square
  // spiral is constructed (hint: it starts at the center) and then create a method to draw one.
  def buildSpiral(loops: Int, width: Int, delta: Int, angle: Angle): List[Instruction] = {
    def buildLoop(width: Int, delta: Int, angle: Angle): List[Instruction] = {
      turn(angle) :: forward(width) :: turn(90.degrees) ::
        forward(width + delta) :: turn(90.degrees) ::
        forward(width + delta * 2) :: turn(90.degrees) ::
        forward(width + delta * 3) :: turn(90.degrees) :: Nil
    }

    loops match {
      case 0 => Nil
      case n => buildLoop(width, delta, angle) ::: buildSpiral(n - 1, width + delta * 4, delta, angle)
    }
  }

  val spiral3 = Turtle.draw(buildSpiral(48, 3, 3, 1.degrees))

  def buildSpiral2(loops: Int, length: Int, delta: Int, angle: Angle): List[Instruction] = {
    def buildCorner(length: Int, angle: Angle): List[Instruction] = {
      forward(length) :: turn(angle) :: Nil
    }

    loops match {
      case 0 => Nil
      case n => buildCorner(length, angle) ::: buildSpiral2(n - 1, length + delta, delta, angle)
    }
  }

  val image = Turtle.draw(buildSpiral2(300, 3, 3, 89.degrees))

  def squareSpiral(steps: Int, distance: Double, angle: Angle, increment: Double): Image = {
    def iter(n: Int, distance: Double): List[Instruction] = {
      n match {
        case 0 => Nil
        case n => forward(distance) :: turn(angle) :: iter(n - 1, distance + increment)
      }
    }

    Turtle.draw(iter(steps, distance))
  }

  def polarPolygon(sides: Int, size: Int): Image = {
    val rotation = Angle.one / sides
    val elts =
      (1 to sides).toList.map { i =>
        PathElement.lineTo(polar(size, rotation * i))
      }
    Image.closedPath(PathElement.moveTo(polar(size, Angle.zero)) :: elts)
  }

  //  def polygonTurtle(sides: Int, size: Int): Image = {
  //    val rotation = Angle.one / sides
  //    val elts = (1 to sides).toList.map(i => List(forward(size), turn(rotation)))
  //
  //    // map non cambia il numero di elementi della lista, quindi come farli raddoppiare
  //    // (per ogni side un forward e un turn)?
  //
  //    Turtle.draw()
  //  }

  def turtlePolygon(sides: Int, sideLength: Double): Image = {
    val rotation = Angle.one / sides

    Turtle.draw((1 to sides).toList.flatMap(s => List(forward(sideLength), turn(rotation))))
  }

  def turtleSquareSpiral(steps: Int, distance: Double, angle: Angle, increment: Double): Image = {
    Turtle.draw((1 to steps).toList.flatMap(s => List(forward(distance + s * increment), turn(angle))))
  }

  def main(args: Array[String]): Unit = {
    //    turtleSquareSpiral(10, 50, 90.degrees, 5).draw()
    squareSpiral(40, 50.0, 65.degrees, 10.0).draw()
  }
}

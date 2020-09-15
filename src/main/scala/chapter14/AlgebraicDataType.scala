package chapter14

import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.turtle.Instruction._
import doodle.turtle._
import doodle.java2d._
import doodle.core.{Angle, Point, Vec}
import doodle.image.Image.Elements.OpenPath

object AlgebraicDataType {

  object Turtle {
    def draw(instructions: List[Instruction]): Image = {

      def iterate(state: TurtleState, instructions: List[Instruction]): List[PathElement] = {
        instructions match {
          case Nil => List.empty[PathElement]
          case ::(head, next) =>
            val (newState, elements) = process(state, head)
            elements ++ iterate(newState, next)
        }
      }

      final case class TurtleState(at: Vec, heading: Angle)

      def process(state: TurtleState, instruction: Instruction): (TurtleState, List[PathElement]) = {
        instruction match {
          case Forward(d) =>
            val nowAt = state.at + Vec.polar(d, state.heading)
            val element = LineTo(nowAt.toPoint)

            (state.copy(at = nowAt), List(element))

          case Turn(a) =>
            val nowHeading = state.heading + a

            (state.copy(heading = nowHeading), List())

          case Branch(instructions) =>
            val branchedElements = iterate(state, instructions)

            (state, MoveTo(state.at.toPoint) :: branchedElements)

          case NoOp => (state, List())
        }
      }

      OpenPath(iterate(TurtleState(Vec.zero, Angle.zero), instructions))
    }


  }

}


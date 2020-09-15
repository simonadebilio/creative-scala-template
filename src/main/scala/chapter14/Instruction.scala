package chapter14

import doodle.core.Angle

sealed trait Instruction

final case class Forward(distance: Double) extends Instruction

final case class Turn(angle: Angle) extends Instruction

final case class Branch(instruction: List[Instruction]) extends Instruction

final case object NoOp extends Instruction

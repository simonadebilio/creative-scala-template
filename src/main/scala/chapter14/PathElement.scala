package chapter14

import doodle.core.Point

sealed trait PathElement// sum type (or)

final case class LineTo(to: Point) extends PathElement // product type (and)

final case class MoveTo(to: Point) extends PathElement

final case class CurveTo(p1: Point, p2: Point, to: Point) extends PathElement

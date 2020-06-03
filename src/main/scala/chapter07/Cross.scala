package chapter07

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object Cross {
  val side = Image.circle(20).strokeWidth(3).strokeColor(Color.blue)
  val center = Image.circle(20).strokeWidth(3).strokeColor(Color.red)

  def crosses(count: Int): Image =
    count match {
      case 0 => center
      case n => side beside crosses(n-1) beside side below side above side
    }
}

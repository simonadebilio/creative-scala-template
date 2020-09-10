package chapter08

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object Chessboard {
  def chessboard(count: Int): Image = {
    val blackSquare = Image.rectangle(30, 30).fillColor(Color.black)
    val whiteSquare = Image.rectangle(30, 30).fillColor(Color.white)
    val base = (whiteSquare beside blackSquare) above (blackSquare beside whiteSquare)
    count match {
      case 0 => base
      case n =>
        val unit = chessboard(n - 1)
        (unit beside unit) above (unit beside unit)
    }
  }

  def newChessboard(count: Int): Image = {
    val blackSquare = Image.rectangle(30, 30).fillColor(Color.black)
    val whiteSquare = Image.rectangle(30, 30).fillColor(Color.white)
    val base = (whiteSquare beside blackSquare) above (blackSquare beside whiteSquare)

    def loop(count: Int): Image = {
      count match {
        case 0 => base
        case n =>
          val unit = loop(n - 1)
          (unit beside unit) above (unit beside unit)
      }
    }
    loop(count)
  }

  def main(args: Array[String]): Unit = {
chessboard(0).draw()
  }
}




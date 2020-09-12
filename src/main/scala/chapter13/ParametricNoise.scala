package chapter13

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._

object ParametricNoise {
  def perturb(point: Point): Random[Point] = {
    val noise = Random.normal(0, 10)
    noise.map(n => Point.cartesian(point.x + n, point.y))
  }

  def perturbedRose2(k: Int): Angle => Random[Point] = {
    (angle: Angle) => {
      perturb(Point.cartesian((angle * k).cos * angle.cos, (angle * k).cos * angle.sin))
    }
  }

  def pawn(parameter: Int): Random[Image] = {
    val image = Random.int.map(f => Image.circle(parameter + f))
    val hue = Random.double.map(d => (parameter * d).degrees)
    val saturation = Random.normal(0, 30)
    val lightness = Random.normal(0, 20)
    val alpha = Random.double
    val color =
      for {
        h <- hue
        s <- saturation
        l <- lightness
        a <- alpha
      } yield Color.hsla(h, s, l, a)

    for {
      i <- image
      c <- color
    } yield i.noFill.strokeColor(c)
  }



  // def randomImage()


  //  def sample(samples: Int, image: Image, curve: Angle => Random[Point]): Image = {
  //    val step = Angle.one / samples
  //
  //    def loop(count: Int): Image = {
  //      val angle = step * count
  //      count match {
  //        case 0 => Image.empty
  //        case n =>
  //          image.
  //            at(curve(angle)).on(loop(n - 1))
  //      }
  //    }
  //
  //    loop(samples)
  //  }


  def allOn(toRandomImages: List[Random[Image]]): Random[Image] = ???

  def main(args: Array[String]): Unit = {
    //    sample(20, Image.circle(10), perturbRose(5)).draw()
  }

}

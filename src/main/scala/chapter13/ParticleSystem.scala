package chapter13

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._

object ParticleSystem {

  val startingPoint: Random[Point] = Random.always(Point.cartesian(0, 0))

  def step(current: Point): Random[Point] = {
    val drift = 10
    val noise: Random[Double] = Random.normal(0, 30)

    noise.map(n => Point.cartesian(current.x + drift + n, current.y + n))
  }

  def render(point: Point): Image =
    Image.circle(10).fillColor(Color.blueViolet).at(point)

  def walk(steps: Int): Random[Image] = {
    def loop(steps: Int, currentPoint: Point): Random[Image] = {
      steps match {
        case 0 => Random.always(Image.empty)
        case n =>
          val next = step(currentPoint)
          next.flatMap(nextPoint => loop(n - 1, nextPoint).map(nextImage => nextImage on render(currentPoint)))
      }
    }

    startingPoint.flatMap(stp => loop(steps, stp))
  }

  def particleSystem(particles: Int, steps: Int): Random[Image] = {
    particles match {
      case 0 => Random.always(Image.empty)
      case n =>
        walk(steps).flatMap(currentWalk => particleSystem(n-1, steps).map(nextWalk => nextWalk on currentWalk))
    }
  }

  def main(args: Array[String]): Unit = {
    particleSystem(10, 10).run.draw()
  }
}

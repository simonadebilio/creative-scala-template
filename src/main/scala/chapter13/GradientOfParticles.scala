package chapter13

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._
import RandomCircles._

object GradientOfParticles {

  val startingPoint: Random[Point] = Random.always(Point.cartesian(0, 0))

  def step(current: Point): Random[Point] = {
    val drift = 10
    val noise: Random[Double] = Random.normal(0, 30)

    noise.map(n => Point.cartesian(current.x + drift + n, current.y + n))
  }

  def render(point: Point): Image = {
    val color = Random.int.map(i => Color.hsl(i.degrees, 0.5, 0.5))

    Image.circle(12).fillColor(color.run).at(point)
  }

  def walk(steps: Int): Random[Image] = {
    def loop(steps: Int, currentPoint: Point): Random[Image] = {
      steps match {
        case 0 => Random.always(Image.empty)
        case n =>
          val next = step(currentPoint)
          for {
            nextPoint <- next
            nextImage <- loop(n - 1, nextPoint)
          } yield nextImage on render(currentPoint)

//          next.flatMap(nextPoint => loop(n - 1, nextPoint).map(nextImage => nextImage on render(currentPoint)))
      }
    }

    startingPoint.flatMap(stp => loop(steps, stp))
  }

  def particleSystem(particles: Int, steps: Int): Random[Image] = {
    particles match {
      case 0 => Random.always(Image.empty)
      case n =>
        for {
          currentWalk <- walk(steps)
          nextWalk <- particleSystem(n-1, steps)
        } yield nextWalk on currentWalk

//        walk(steps).flatMap(currentWalk => particleSystem(n-1, steps).map(nextWalk => nextWalk on currentWalk))
    }
  }

  def main(args: Array[String]): Unit = {
    particleSystem(1, 30).run.draw()
  }
}

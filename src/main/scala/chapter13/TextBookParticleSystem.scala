package chapter13

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.random._

object TextBookParticleSystem {

  val start = Random.always(Point.zero)

  def step(current: Point): Random[Point] = {
    val drift = Point(current.x + 10, current.y)
    val noise =
      Random.normal(0.0, 5.0) flatMap { x =>
        Random.normal(0.0, 5.0) map { y =>
          Vec(x, y)
        }
      }
    noise.map(vec => drift + vec)
  }

  def render(point: Point): Image = {
    val length = (point - Point.zero).length
    val sides = (length / 20).toInt + 3
    val hue = (length / 200).turns
    val color = Color.hsl(hue, 0.7, 0.5)
    Image
      .star(sides, 5, 3, 0.degrees)
      .noFill
      .strokeColor(color)
      .at(point.toVec)
  }


  def walk(steps: Int): Random[Image] = {
    def loop(count: Int, current: Point, image: Image): Random[Image] = {
      count match {
        case 0 => Random.always(image on render(current))
        case n =>
          val next = step(current)
          next.flatMap { pt =>
            loop(count - 1, pt, image on render(current))
          }
      }
    }

    start.flatMap { pt => loop(steps, pt, Image.empty) }
  }

  def particleSystem(particles: Int, steps: Int): Random[Image] = {
    particles match {
      case 0 => Random.always(Image.empty)
      case n => walk(steps).flatMap { img1 =>
        particleSystem(n - 1, steps) map { img2 =>
          img1 on img2
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    (particleSystem(10, 10).run).draw()
  }

}
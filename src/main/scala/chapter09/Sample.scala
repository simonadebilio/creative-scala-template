package chapter09

import doodle.core.{Angle, Point}
import doodle.image.Image

object Sample {
  def sample(samples: Int, image: Image, curve: Angle => Point): Image = {
    val step = Angle.one / samples

    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n =>
          image.at(curve(angle)).on(loop(n - 1))
      }
    }

    loop(samples)
  }
}

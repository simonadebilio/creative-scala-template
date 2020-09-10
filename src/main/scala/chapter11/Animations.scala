package chapter11

import chapter09.Sample.sample
import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.reactor._
import doodle.interact.syntax._
import doodle.core.{Angle, Point}

object Animations {
  val travellingCircle =
    Reactor.init(Point(-300, 0))
      .onTick(pt => Point(pt.x + 1, pt.y))
      .render(pt => Image.circle(10).at(pt))
      .stop(pt => pt.x >= 300)

//  travellingCircle.run(Frame.size(600, 600))

  val orbitingCircle =
    Reactor.init(Point(0, 300))
      .onTick(pt => pt.rotate(2.degrees))
      .render(pt => Image.circle(10).at(pt))

//  orbitingCircle.run(Frame.size(600, 600))

  import chapter09.Rose._

  val orbitingRose =
    Reactor.init(Point.polar(500, 0.degrees))
      .onTick(pt => rose(1000)(pt.rotate(1.degrees).angle))
      .render(pt => Image.circle(10).at(pt))

  val bubble =
    Reactor.linearRamp(0, 200)
      .render(r => Image.circle(r))


  def main(args: Array[String]): Unit = {
    bubble.draw()
  }

}

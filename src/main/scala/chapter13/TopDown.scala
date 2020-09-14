//package chapter13
//
//import doodle.image._
//import doodle.core._
//import doodle.random._
//import doodle.image.syntax.core._
//
//
//object TopDown {
//
//  def result(
//              image: List[Random[Image]],
//              points: List[Random[Point]],
//              path: Angle => Random[Point],
//              rotation: Angle
//            ): Random[Image] = ???
//
//
//  val startingPoint = Point.cartesian(0, 0)
//
//  def pathPerturbation(point: Point): Random[Point] = {
//    val perturbation = Random.normal(0, 30)
//
//    perturbation.map(p => Point.cartesian(point.x * p, point.y + p))
//  }
//
//  def perturbedPath(parameter: Int): Angle => Random[Point] = {
//    (angle: Angle) => {
//      pathPerturbation(
//        Point.cartesian(
//        (angle * parameter).cos * angle.cos,
//        (angle * parameter).cos * angle.sin)
//      )
//    }
//  }
//
//  def listOfPoints(numberOfPoints: Int): List[Random[Point]] = {
//    (1 until numberOfPoints).toList.map(n =>
//      pathPerturbation(Point.cartesian(startingPoint.x + n, startingPoint.y * n)))
//  }
//
//  def image(parameter: Point): Random[Image] = {
//    val image = Random.int.map(f => Image.circle(f))
//    val hue = Random.double.map(d => d.degrees)
//    val saturation = Random.normal(0, 30)
//    val lightness = Random.normal(0, 20)
//    val alpha = Random.double
//    val color =
//      for {
//        h <- hue
//        s <- saturation
//        l <- lightness
//        a <- alpha
//      } yield Color.hsla(h, s, l, a)
//
//    for {
//      i <- image
//      c <- color
//    } yield i.noFill.strokeColor(c)
//  }
//
//  def toImage(randomImage: Random[Image]): Image = ???
//    randomImage
//
//  def toImages(numberOfPoints: List[Random[Point]]): List[Random[Image]] = ???
//     numberOfPoints.flatMap(nopt => nopt.flatMap(pt => image(pt)))
//
//
//
//
//
//  def allOn(listOfPoints: List[Random[Point]]): Random[Image] =
//    listOfPoints match {
//      case Nil => Random.always(Image.empty)
//      case ::(head, next) =>
//
//    img.flatMap(i => allOn(imgs).map(is => (i on is)))
//    }
//
//
//}

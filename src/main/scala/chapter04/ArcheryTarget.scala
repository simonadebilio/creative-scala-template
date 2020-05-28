import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object ArcheryTarget {
    val target = Image.circle(400).fillColor(Color.white)
            .under(Image.circle(300).fillColor(Color.black))
            .under(Image.circle(200).fillColor(Color.blue))
            .under(Image.circle(100).fillColor(Color.red))
            .under(Image.circle(50).fillColor(Color.yellow))

    val stand = Image.rectangle(50,100).fillColor(Color.brown)
            .above(Image.rectangle(150,50).fillColor(Color.brown))

    val grass = Image.rectangle(400,100).fillColor(Color.green)

    val fullImage = target above stand above grass
}

import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._

object Street {
    val wall1 = Image.rectangle(20, 32).fillColor(Color.coral).noStroke
    val wall2 = Image.rectangle(20, 32).fillColor(Color.coral).noStroke
    val wall3 = Image.rectangle(50, 20).fillColor(Color.coral).noStroke
    val door = Image.rectangle(10, 30).fillColor(Color.black)
    val walls = (wall1 beside door beside wall2) below wall3
    val roof = Image.triangle(50, 30).fillColor(Color.red)
    val house = walls below roof

    val trunk = Image.rectangle(12, 35).fillColor(Color.brown)
    val leaves = Image.circle(45).fillColor(Color.green)
    val tree = trunk below leaves

    val road = Image.rectangle(50, 5).fillColor(Color.yellow)
                .beside(Image.rectangle(20, 5).fillColor(Color.black))
                .above(Image.rectangle(300, 10).fillColor(Color.black))

    val complex = road below (house beside tree)

    val fullImage = complex beside complex beside complex
}

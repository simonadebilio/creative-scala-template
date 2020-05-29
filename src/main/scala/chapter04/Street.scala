package chapter04

import doodle.core._
import doodle.image._

object Street {
  val wall1 = Image.rectangle(20, 32).fillColor(Color.coral).noStroke
  val wall2 = Image.rectangle(20, 32).fillColor(Color.coral).noStroke
  val wall3 = Image.rectangle(50, 20).fillColor(Color.coral).noStroke
  val door = Image.rectangle(10, 32).fillColor(Color.black).noStroke
  val walls = (wall1 beside door beside wall2) below wall3
  val roof = Image.triangle(50, 30).fillColor(Color.red).noStroke
  val house = walls below roof

  val trunk = Image.rectangle(12, 35).fillColor(Color.brown).noStroke
  val leaves = Image.circle(45).fillColor(Color.green).noStroke
  val tree = trunk below leaves

  val road = ((Image.rectangle(70, 5).fillColor(Color.yellow).noStroke
    beside Image.rectangle(25, 5).fillColor(Color.black)).noStroke
    above Image.rectangle(95, 10).fillColor(Color.black)).noStroke

  val complex = road below (house beside tree)

  val fullImage = complex beside complex beside complex
}

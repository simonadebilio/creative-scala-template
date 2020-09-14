package chapter13

object Substring {

  def substring(s: String): List[String] = {
    s match {
      case "" => Nil
      case w => {
        val tmp = w.drop(1)
        tmp :: substring(tmp)
      }
    }
  }

  val novel: List[String] = "simona" :: "luca" :: Nil

  def substrings(ws: List[String]): List[String] = {
    ws.flatMap(w => substring(w))
  }

  def prependDot(s: String): String =
    "." + s

  def myConcat(s1: String, s2: String): String = s"$s1-$s2"

  def exercise(words: List[String]): List[String] = {
    val ss = substrings(words)
    println(ss)

    ss.flatMap(s => substring(s)
      .map(wrd => myConcat(s, wrd)))
  }

  def main(args: Array[String]): Unit = {
    println(exercise(novel))
  }
}

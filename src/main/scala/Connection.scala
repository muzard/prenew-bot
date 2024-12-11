package Connection

import scala.io.Source


def getToken: String =
  val path = "C:\\Users\\arttu\\ideaProjects\\TGbotti-prenew\\src\\main\\scala\\.env"
  val iterator = Source.fromFile(path)
  val token = iterator.getLines().toVector.apply(0)
  iterator.close()
  token



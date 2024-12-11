package Connection

import scala.io.Source

import sttp.client3._
import io.circe._
import io.circe.parser._
import io.circe.generic.auto._

case class Update(update_id: Int, message: Option[Message])
case class Message(message_id: Int, text: Option[String], chat: Chat)
case class Chat(id: Long)

object TelegramBotApi:
  def getToken: String =
    val path = "C:\\Users\\arttu\\ideaProjects\\TGbotti-prenew\\src\\main\\scala\\.env"
    val iterator = Source.fromFile(path)
    val token = iterator.getLines().toVector.apply(0)
    iterator.close()
    token

  def getBotURL: String = s"https://api.telegram.org/bot$getToken"

  def getFileURL: String = s"https://api.telegram.org/file/bot$getToken"

  implicit val backend: SttpBackend[Identity, Any] = HttpURLConnectionBackend()

  def getUpdates: Option[Json] =
    val request = basicRequest.get(uri"$getBotURL/getUpdates").send(backend)

    var result: Option[Json] = None

    request.body match
      case Left(body) =>
        println("left")
        println(body)
      case Right(body) =>
        println("right")
        println(body)

        parse(body) match
          case Left(error) =>
            println("parsing failure")
            println(error)
          case Right(json) =>
            println("json parsed")
            result = Some(json)

    result

  def getFilePath(json: Option[Json]): String =
    var returnable: Option[String] = None

    json match
      case Some(parsedJson) =>
        returnable = parsedJson
          .hcursor
          .downField("result")
          .downArray
          .downField("photo")
          .downArray
          .downField("file_id")
          .as[String]
          .toOption

    returnable match
      case Some(string) => string
      case None => "problem in getFilePath"

  def fetchPhoto(filePath: String): Unit =
    val request = basicRequest.get(uri"$getFileURL/$filePath")
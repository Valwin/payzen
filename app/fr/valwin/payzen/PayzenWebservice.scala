package fr.valwin.payzen

import play.api.libs.ws.{Response, WS}
import play.mvc.Http
import play.api.templates.Xml
import scala.concurrent.Await
import scala.concurrent.duration._
import play.api.Logger

/**
 * @author Valentin Kasas
 */
object PayzenWebservice {

  val baseUrl = "https://secure.payzen.eu/vads-ws/v3"
  val timeout = 30.seconds

  def performRequest(body: Xml) = WS.url(baseUrl)
    .withHeaders(
      Http.HeaderNames.CONTENT_TYPE ->  "text/xml; charset=utf-8",
      "SOAPAction" -> ""
    ).post(body)

  def analyzeResponse(response: Response):Either[String, Map[String, String]] = {
     if(Set(200, 201).contains(response.status)){
       val ret = (response.xml \\ "return")
       Right(ret(0).map{elem =>
         elem.label -> elem.text
       }.toMap)
     } else {
       Left("Communication Error")
     }
  }

  def modifyAndValidate(shopId: String, transDate: javax.xml.datatype.XMLGregorianCalendar, transId: String, seqNb: Int, ctxMode: String, amount: Long, devise: Int,  remiseDate: javax.xml.datatype.XMLGregorianCalendar, comment: String, signature: String ) = {
    val responseFuture = performRequest(views.xml.modifyAndValidateEnveloppe(shopId, transDate, transId, seqNb, ctxMode, amount, devise,  remiseDate, comment, signature))
    analyzeResponse(Await.result(responseFuture, timeout))
  }

  def cancel(shopId: String, transDate: javax.xml.datatype.XMLGregorianCalendar, transId: String, seqNb: Int, ctxMode: String, comment: String, signature: String) = {
    val responseFuture = performRequest(views.xml.cancelEnveloppe(shopId, transDate, transId, seqNb, ctxMode, comment, signature))
    analyzeResponse(Await.result(responseFuture, timeout))
  }
}

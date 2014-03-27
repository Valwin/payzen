package scalaxb


/**
 * @author Valentin Kasas
 */
trait CustomHttpClients extends HttpClients{
  lazy val httpClient = new CustomHttpClient {}

  trait CustomHttpClient extends HttpClient {
    import dispatch._, Defaults._
    val http = new Http()

    def request(in: String, address: java.net.URI, headers: Map[String, String]): String = {
      val cleanIn = in.replaceAll("tns:shopId", "shopId")
        .replaceAll("tns:transmissionDate", "transmissionDate")
        .replaceAll("tns:transactionId", "transactionId")
        .replaceAll("tns:sequenceNb", "sequenceNb")
        .replaceAll("tns:ctxMode", "ctxMode")
        .replaceAll("tns:comment", "comment")
        .replaceAll("tns:wsSignature", "wsSignature")
        .replaceAll("tns:remiseDate", "remiseDate")
        .replaceAll("tns:amount", "amount")
        .replaceAll("tns:devise", "devise")
      val req = url(address.toString) << cleanIn <:< headers
      val s = http(req > as.String)
      s()
    }
  }
}

package fr.valwin.payzen

import play.api.{Plugin, Application}
import com.typesafe.config.ConfigValue

/**
 * @author Valentin Kasas
 */
class PayzenPlugin(app: Application) extends Plugin {
  private [this] var certificate: String = ""

  def getCertificate = certificate

  lazy val defaultParameters = (for {
    defaults <- app.configuration.getObject("payzen.defaults").toSeq
    property <- defaults.entrySet.toArray
  } yield {
    val entry = property.asInstanceOf[java.util.Map.Entry[String,ConfigValue]]
    entry.getKey -> entry.getValue.unwrapped.toString
  }).toMap[String, String]

  override def onStart() = {
    super.onStart()
    certificate = app.configuration.getString("payzen.certificate").getOrElse(certificate)
  }
}

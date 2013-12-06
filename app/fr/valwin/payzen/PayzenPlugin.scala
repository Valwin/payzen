package fr.valwin.payzen

import play.api.{Plugin, Application}

/**
 * @author Valentin Kasas
 */
class PayzenPlugin(app: Application) extends Plugin {

  private [this] var certificate: String = ""

  def getCertificate = new String(certificate)

  override def onStart() = {
    super.onStart()
    certificate = app.configuration.getString("payzen.certificate").getOrElse(certificate)
  }
}

package fr.valwin.payzen

import org.specs2.mutable.Specification
import org.specs2.matcher.ShouldMatchers

/**
 * @author Valentin Kasas
 */
class SignatureSpec extends Specification with ShouldMatchers {

  "Signature" should {
    "behave like the documentation's example" in {
      val data = Map (
        "vads_version" -> "V2",
        "vads_page_action" -> "PAYMENT",
        "vads_action_mode" -> "INTERACTIVE",
        "vads_payment_config" -> "SINGLE",
        "vads_site_id" -> "12345678",
        "vads_ctx_mode" -> "TEST",
        "vads_trans_id" -> "654321",
        "vads_trans_date" -> "20090501193530",
        "vads_amount" -> "1524",
        "vads_currency" -> "978"
      )

      val certificate = "1122334455667788"
      val hash:String = Signature.computeHash(data, certificate)
      hash shouldEqual("606b369759fac4f0864144c803c73676cbe470ff")
    }
  }

}

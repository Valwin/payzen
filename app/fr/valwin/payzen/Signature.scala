package fr.valwin.payzen

import java.security.MessageDigest


/**
 * @author Valentin Kasas
 */
object Signature {

  def computeHash(data: Map[String, String], certificate: String) = {
    val toHash = data.toList.sortBy(_._1).foldRight(certificate)((e, a) => s"${e._2}+$a").getBytes("UTF-8")
    val md = MessageDigest.getInstance("SHA-1")
    md.update(toHash, 0, toHash.length)
    val sha1 = md.digest()
    sha1.map{ byte =>
      Integer.toHexString((byte >> 4) & 0xF )  + Integer.toHexString(byte & 0xF)
    }.fold("")(_ + _)
  }


}

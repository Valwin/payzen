package views.html

import play.api.templates.Html

/**
 * @author Valentin Kasas
 */
object PayzenFormHelper {

  def formFields(data: Map[String,String]): Html =
    data.toList.foldLeft(Html("")){
      case (html, keyvalue)=>
        html += Html(s"""<input type="hidden" name="${keyvalue._1}" value="${keyvalue._2}"/>""")
    }

}

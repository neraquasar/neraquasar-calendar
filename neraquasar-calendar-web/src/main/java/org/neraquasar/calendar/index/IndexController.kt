package org.neraquasar.calendar.index

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 *
 *
 * @author Konstantin Valer'evich Dichenko
 * Created 11.06.2017
 */
@Controller
class IndexController {

    @RequestMapping(value = "/")
    fun index(model: Model): String {
        return "index"
    }
}
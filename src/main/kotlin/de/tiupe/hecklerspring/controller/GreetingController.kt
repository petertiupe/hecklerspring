package de.tiupe.hecklerspring.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.web.bind.annotation.RequestMapping


@RestController
@RequestMapping("/greeting")
class GreetingController {

    // Achtung, in Kotlin muss man das $-Zeichen escapen
    // mit dem Doppelpunkt wird ein Default-Wert abgetrennt

    @Value("\${gruss.morgens: Hallo}")
    lateinit var  morgengruss: String

    @Value("\${gruss.morgens: Hallo}")
    lateinit var  mittagsgruss: String

    @Value("\${gruss.morgens: Hallo}")
    lateinit var  abendgruss: String

    @Value("\${gruss.abhaengigkeit.a-morgens}")
    lateinit var fruehstueck: String

    @Value("\${gruss.abhaengigkeit.a-mittags}")
    lateinit var mittagessen: String

    @Value("\${gruss.abhaengigkeit.a-abends}")
    lateinit var abendbrot: String




    @GetMapping("/gruss")
    fun getGruss(): String {
        val now = Clock.System.now()
        val hourOfDay: Int = now.toLocalDateTime(TimeZone.currentSystemDefault()).hour
        if(hourOfDay < 12) {
            return morgengruss
        } else if (hourOfDay in 12..17) {
            return mittagsgruss
        } else {
            return abendgruss
        }
    }

    @GetMapping("/essensgruss")
        fun getGrussMitEssen(): String {
            val now = Clock.System.now()
            val hourOfDay: Int = now.toLocalDateTime(TimeZone.currentSystemDefault()).hour
            if(hourOfDay < 12) {
                return fruehstueck
            } else if (hourOfDay in 12..17) {
                return mittagessen
            } else {
                return abendbrot
            }
        }



}
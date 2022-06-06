package de.tiupe.hecklerspring.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Entity
import javax.persistence.Id

/*
* Unter Baeldungs - Seite
*
*   https://www.baeldung.com/kotlin/jpa
*
* wird davor gewarnt, data-Classes als JPA-Entitys zu verwenden.
* Die generierten equals und hashcode-Funktionen reichen nicht aus, um die
* Ansprüche von Spring an allen Stellen zu erfüllen.
*
* Ich habe überlegt, eine Wrapper - Klasse zu nutzen, das muss aber wohl getestet sein.
* */

// Die Variablen werden derzeit nirgends einzeln abgefragt
@Suppress("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
class Article(
    @Id val id: String,
    val url: String,
    val title: String,
    val summary: String)
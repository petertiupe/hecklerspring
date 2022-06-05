package de.tiupe.hecklerspring.database

import de.tiupe.hecklerspring.entity.Article
import org.springframework.data.repository.CrudRepository

/* Das Repository ist die Schnittstelle zur DB. Sie abstrahiert den Treiber etc. man
   muss keine weitere Konfiguration durchführen.

   Da das Repository von einer Bean-Klasse ableitet, scheint sie keine Annotation zu benötigen
   und funktioniert trotzdem.

   Das Paket configuration ist daher auch nicht ganz korrekt.
*/

interface ArticleRepository : CrudRepository<Article, String> {
}
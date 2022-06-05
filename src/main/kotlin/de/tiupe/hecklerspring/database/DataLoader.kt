package de.tiupe.hecklerspring.database

import de.tiupe.hecklerspring.entity.Article
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class DataLoader(val articleRepository: ArticleRepository) {
    @PostConstruct
    private fun loadData() {
        this.articleRepository.saveAll(
            listOf(
                Article("1", "http://www.tiupe.de", "Tina und Peter", "Die beste Website der Welt stellt sich vor"),
                Article("2", "http://www.spiegel.de", "Spiegel", "Eines der wöchentlichen Nachrichtenmagazine in Deutschland")
            )
        )
    }
}
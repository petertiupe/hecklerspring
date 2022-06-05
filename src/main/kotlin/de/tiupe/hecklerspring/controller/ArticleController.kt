package de.tiupe.hecklerspring.controller

import de.tiupe.hecklerspring.entity.Article
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController {
    private var articles: List<Article> = listOf(
        Article("1", "http://www.tiupe.de", "Tina und Peter", "Die beste Website der Welt stellt sich vor"),
        Article("2", "http://www.spiegel.de", "Spiegel", "Eines der wöchentlichen Nachrichtenmagazine in Deutschland")
    )

    // @RequestMapping
    @GetMapping
    fun getArticles(): Iterable<Article> {
        return this.articles
    }

    @GetMapping("/{id}")
    fun getArticle(@PathVariable id: String): Article {
        return this.articles.first { it.id == id }
    }

    @PostMapping
    fun postArticle(@RequestBody article: Article): Article {
        this.articles += article
        return article
    }

    @PutMapping("/{id}")
    fun putArticle(@PathVariable id: String, @RequestBody article: Article): ResponseEntity<Article> {
        var hasBeenReplaced = false
        this.articles = this.articles.map{
            if(article.id == it.id){
                hasBeenReplaced = true
                article
            } else {
                it
            }
        }
        if(!hasBeenReplaced) {
            // es gab den Artikel noch nicht.
            val articleToReturn = this.postArticle(article)
            return ResponseEntity(articleToReturn, HttpStatus.CREATED)
        }
        return ResponseEntity.ok(article)
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: String) {
        this.articles = this.articles.filter{ it.id != id }
    }
}
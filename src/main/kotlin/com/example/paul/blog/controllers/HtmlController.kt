package com.example.paul.blog.controllers

import com.example.paul.blog.config.BlogProperties
import com.example.paul.blog.models.Article
import com.example.paul.blog.models.User
import com.example.paul.blog.repositories.ArticleRepository
import com.example.paul.blog.utils.format
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HtmlController(private val articleRepo: ArticleRepository,
                     private val properties: BlogProperties) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["articles"] = articleRepo.findAllByOrderByAddedAtDesc()
                .map { it.render() }

        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String,
                model: Model): String {
        val article = articleRepo.findBySlug(slug)
                ?.render()
                ?: throw IllegalArgumentException("Wrong article slug provided")

        model["title"] = article.title
        model["article"] = article

        return "article"
    }

    fun Article.render() = RenderedArticle(
            slug,
            title,
            headline,
            content,
            url,
            author,
            addedAt.format()
    )

    data class RenderedArticle(
            val slug: String,
            val title: String,
            val headline: String,
            val content: String,
            var url: String,
            val author: User,
            val addedAt: String
    )
}

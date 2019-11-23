package com.example.paul.blog.repositories

import com.example.paul.blog.models.Article
import com.example.paul.blog.models.User
import org.springframework.data.repository.CrudRepository

interface ArticleRepository : CrudRepository<Article, Long> {

    fun findBySlug(slug: String): Article?

    fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository : CrudRepository<User, Long> {

    fun findByLogin(login: String): User?
}

package com.example.paul.blog.models

import com.example.paul.blog.utils.toSlug
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Article(
        var title: String,
        var headline: String,
        var content: String,
        var url: String,
        @ManyToOne
        var author: User,
        var slug: String = title.toSlug(),
        var addedAt: LocalDateTime = LocalDateTime.now(),
        @Id
        @GeneratedValue
        var id: Long? = null
)

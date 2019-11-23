package com.example.paul.blog.repositories

import com.example.paul.blog.models.Article
import com.example.paul.blog.models.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
internal class ArticleRepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val spotty = User("spottyg", "Spotty", "Giraffe")
        entityManager.persist(spotty)

        val firstArticle = Article(
                "Rothschild's Giraffe",
                "Giraffa camelopardalis rothschildi",
                "It is one of the most endangered distinct populations of giraffe ...",
                "a url",
                spotty)
        entityManager.persist(firstArticle)
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(firstArticle.id!!)
        assertThat(found).isEqualTo(firstArticle)
    }

    @Test
    fun `When findByLogin then return User`() {
        val spotty = User("spottyg", "Spotty", "Giraffe")
        entityManager.persist(spotty)
        entityManager.flush()

        val user = userRepository.findByLogin(spotty.login)
        assertThat(user).isEqualTo(spotty)
    }
}

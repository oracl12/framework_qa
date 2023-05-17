package util

import models.Card
import models.Comment
import models.Result
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object HibernateUtil {
    private val configuration = Configuration()

    init {
        configuration.configure("hibernate.cfg.xml")
        configuration.addAnnotatedClass(Card::class.java)
        configuration.addAnnotatedClass(Comment::class.java)
        configuration.addAnnotatedClass(Result::class.java)
    }

    fun getSessionFactory(): SessionFactory {
        return configuration.buildSessionFactory()!!
    }
}
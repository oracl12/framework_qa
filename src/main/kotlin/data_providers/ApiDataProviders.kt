package data_providers

import models.Card
import models.Comment
import org.testng.annotations.DataProvider
import util.HibernateUtil

object ApiDataProviders {
    @JvmStatic
    @DataProvider(name = "card-test-data")
    fun setCardData(): Array<Card> {
        val session = HibernateUtil.getSessionFactory().openSession()
        val query = session.createQuery("FROM Card", Card::class.java)
        return query.list().toTypedArray()
    }

    @JvmStatic
    @DataProvider(name = "comment-test-data")
    fun setCommentData(): Array<Comment> {
        val session = HibernateUtil.getSessionFactory().openSession()
        val query = session.createQuery("FROM Comment", Comment::class.java)
        return query.list().toTypedArray()
    }
}
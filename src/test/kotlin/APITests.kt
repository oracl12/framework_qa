
import constants.SharedTrelloAPIConstants
import data_providers.ApiDataProviders
import models.Card
import models.Comment
import io.restassured.RestAssured
import listeners.TestResultListener
import org.testng.AssertJUnit
import org.testng.annotations.*
import requests.TrelloAPIRequest

@Listeners(TestResultListener::class)
class APITests {
    private var cardId: String? = null
    private var commentId: String? = null

    @BeforeSuite
    fun setUpConf(){
        RestAssured.baseURI = SharedTrelloAPIConstants.baseUri
    }
    @Test(
        dataProvider = "card-test-data",
        dataProviderClass = ApiDataProviders::class,
        description="Creation of new card on existing board"
    )
    fun createNewCard(card: Card) {
        val response = TrelloAPIRequest().createNewCard(card)

        AssertJUnit.assertEquals(response.statusCode, 200)
        cardId = response.jsonPath().get("id")
        AssertJUnit.assertEquals(response.jsonPath().get("name"), card.name)
    }

    @Test(
        dependsOnMethods = ["createNewCard"],
        dataProvider = "comment-test-data",
        dataProviderClass = ApiDataProviders::class,
        description="Adding new comment on existing card")
    fun addCommentToCard(comment: Comment) {
        val response = TrelloAPIRequest().addCommentToCard(comment, cardId)

        AssertJUnit.assertEquals(response.statusCode, 200)
        commentId = response.jsonPath().get("id")
        AssertJUnit.assertEquals(response.jsonPath().get("data.text"), comment.text)
    }

    @Test(
        dependsOnMethods = ["addCommentToCard"],
        dataProviderClass = ApiDataProviders::class,
        description="Deleting comment")
    fun deleteComment() {
        val response = TrelloAPIRequest().deleteComment(commentId)

        AssertJUnit.assertEquals(response.statusCode, 200)
        AssertJUnit.assertNull(response.jsonPath().get("_value"))
    }
}
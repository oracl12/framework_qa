package requests

import constants.SharedTrelloAPIConstants
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import models.Card
import models.Comment

class TrelloAPIRequest {
    fun createNewCard(card: Card): Response {
        val requestBody = """
            {
                "name": "${card.name}",
                "description": "${card.description}",
                "pos": "${card.position}"
            }
        """.trimIndent()

        return RestAssured.given()
            .queryParam("key", SharedTrelloAPIConstants.apiKey)
            .queryParam("token", SharedTrelloAPIConstants.apiToken)
            .queryParam("idList", SharedTrelloAPIConstants.boardId)
            .`when`()
            .body(requestBody)
            .contentType(ContentType.JSON)
            .post("/cards")
            .then()
            .extract().response()
    }

    fun addCommentToCard(comment: Comment, cardId: String?): Response {
        return RestAssured.given()
            .queryParam("key", SharedTrelloAPIConstants.apiKey)
            .queryParam("token", SharedTrelloAPIConstants.apiToken)
            .queryParam("text", comment.text)
            .contentType(ContentType.JSON)
            .`when`()
            .post("/cards/$cardId/actions/comments")
            .then()
            .extract().response()
    }

    fun deleteComment(commentId: String?): Response {
        return RestAssured.given()
            .queryParam("key", SharedTrelloAPIConstants.apiKey)
            .queryParam("token", SharedTrelloAPIConstants.apiToken)
            .contentType(ContentType.JSON)
            .`when`()
            .delete("/actions/$commentId")
            .then()
            .extract().response()
    }
}

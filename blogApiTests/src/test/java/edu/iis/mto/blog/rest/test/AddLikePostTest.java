package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AddLikePostTest extends FunctionalTests {

    @Test
    public void addLikeToBlogPostWithoutLikesByOwnerOfPostShouldReturnBadRequestStatus() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.ADD_LIKE_BY_OWNER_TO_POST_WITHOUT_LIKES);
    }

    @Test
    public void addLikeToBlogPostWithLikesByOwnerOfPostShouldReturnBadRequestStatus() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.ADD_LIKE_BY_OWNER_TO_POST_WITH_LIKES);
    }

    @Test
    public void addLikeToBlogPostWithoutLikesByUserWithConfirmAccountStatusShouldReturnOkStatus() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .post(StaticElements.ADD_LIKE_BY_CONFIRM_USER_TO_POST_WITHOUT_LIKES);
    }

    @Test
    public void addLikeToBlogPostWithLikesByUserWithConfirmAccountStatusShouldReturnOkStatus() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .post(StaticElements.ADD_LIKE_BY_CONFIRM_USER_TO_POST_WITH_LIKES);
    }

    @Test
    public void addLikeToBlogPostWithoutLikesByUserWithNewAccountStatusShouldReturnBadRequestStatus() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.ADD_LIKE_BY_NEW_USER_TO_POST_WITHOUT_LIKES);
    }

    @Test
    public void addLikeToBlogPostWithLikesByUserWithNewAccountStatusShouldReturnBadRequestStatus() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.ADD_LIKE_BY_NEW_USER_TO_POST_WITH_LIKES);
    }

    @Test
    public void addLikeToBlogPostWithoutLikesByUserWithRemoveAccountStatusShouldReturnBadRequestStatus() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.ADD_LIKE_BY_REMOVE_USER_TO_POST_WITHOUT_LIKES);
    }

    @Test
    public void addLikeToBlogPostWithLikesByUserWithRemoveAccountStatusShouldReturnBadRequestStatus() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.ADD_LIKE_BY_REMOVE_USER_TO_POST_WITH_LIKES);
    }

    @Test
    public void addTwoLikesToBlogPostWithoutLikesByUserShouldNotChangeLikesCountOfBlogPost() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .post(StaticElements.ADD_LIKE_BY_CONFIRM_USER_TO_POST_WITHOUT_LIKES);

        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .post(StaticElements.ADD_LIKE_BY_CONFIRM_USER_TO_POST_WITHOUT_LIKES);

        given()
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(StaticElements.GET_BLOG_POST_WITHOUT_LIKES_INFO)
                .then()
                .body("likesCount", is(equalTo(1)));
    }

    @Test
    public void addTwoLikesToBlogPostWithLikesByUserShouldNotChangeLikesCountOfBlogPost() {
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .post(StaticElements.ADD_LIKE_BY_CONFIRM_USER_TO_POST_WITH_LIKES);

        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .post(StaticElements.ADD_LIKE_BY_CONFIRM_USER_TO_POST_WITH_LIKES);

        given()
                .accept(ContentType.JSON)
                .header("Content-Type", "application/json;charset=UTF-8")
                .expect()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get(StaticElements.GET_BLOG_POST_WITH_LIKES_INFO)
                .then()
                .body("likesCount", is(equalTo(2)));
    }
}

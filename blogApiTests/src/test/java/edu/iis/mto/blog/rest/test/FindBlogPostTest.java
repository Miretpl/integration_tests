package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class FindBlogPostTest extends FunctionalTests {

    @Test
    public void shouldReturnOkStatusWithTwoSizeListWhenOwnerLookingForBlogPosts() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.GET_BLOG_POST_BY_OWNER)
               .then()
               .body("size()", is(2));
    }

    @Test
    public void shouldReturnOkStatusWithZeroSizeListWhenConfirmUserWithoutPostsLookingForBlogPosts() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.GET_BLOG_POST_BY_CONFIRM_USER_WITHOUT_POSTS)
               .then()
               .body("size()", is(0));
    }

    @Test
    public void shouldReturnOkStatusWithOneSizeListWhenConfirmUserWithPostsLookingForBlogPosts() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.GET_BLOG_POST_BY_CONFIRM_USER_WITH_POSTS)
               .then()
               .body("size()", is(1));
    }

    @Test
    public void shouldReturnOkStatusWithOneSizeListWhenNewUserWithPostsLookingForBlogPosts() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.GET_BLOG_POST_BY_NEW_USER_WITH_POSTS)
               .then()
               .body("size()", is(1));
    }

    @Test
    public void shouldReturnOkStatusWithZeroSizeListWhenNewUserWithoutPostsLookingForBlogPosts() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.GET_BLOG_POST_BY_NEW_USER_WITHOUT_POSTS)
               .then()
               .body("size()", is(0));
    }

    @Test
    public void shouldReturnBadRequestStatusWhenRemovedUserWithPostsLookingForBlogPosts() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .get(StaticElements.GET_BLOG_POST_BY_REMOVE_USER_WITH_POSTS);
    }

    @Test
    public void shouldReturnBadRequestStatusWhenRemovedUserWithoutPostsLookingForBlogPosts() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .get(StaticElements.GET_BLOG_POST_BY_REMOVE_USER_WITHOUT_POSTS);
    }

    @Test
    public void shouldReturnBadRequestStatusWhenNotExistingUserLookingForBlogPosts() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .get(StaticElements.GET_BLOG_POST_BY_NOT_EXISTING_USER);
    }
}

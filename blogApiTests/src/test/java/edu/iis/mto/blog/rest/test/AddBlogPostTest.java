package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddBlogPostTest extends FunctionalTests {

    @Test
    public void createBlogPostByUserWithStatusConfirmedShouldReturnCreated() {
        JSONObject jsonObj = new JSONObject().put("entry", "new blog post");
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .body(jsonObj.toString())
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_CREATED)
               .when()
               .post(StaticElements.CREATE_BLOG_POST_BY_CONFIRM_USER);
    }

    @Test
    public void createBlogPostByUserWithStatusNewShouldReturnBadRequest() {
        JSONObject jsonObj = new JSONObject().put("entry", "new blog post");
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .body(jsonObj.toString())
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.CREATE_BLOG_POST_BY_NEW_USER);
    }

    @Test
    public void createBlogPostByUserWithStatusRemoveShouldReturnBadRequest() {
        JSONObject jsonObj = new JSONObject().put("entry", "new blog post");
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .body(jsonObj.toString())
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.CREATE_BLOG_POST_BY_REMOVE_USER);
    }

    @Test
    public void createBlogPostByUserNotExistingInDatabaseShouldReturnBadRequest() {
        JSONObject jsonObj = new JSONObject().put("entry", "new blog post");
        given().accept(ContentType.JSON)
               .header("Content-Type", "application/json;charset=UTF-8")
               .body(jsonObj.toString())
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_BAD_REQUEST)
               .when()
               .post(StaticElements.CREATE_BLOG_POST_BY_NOT_EXISTING_USER);
    }
}

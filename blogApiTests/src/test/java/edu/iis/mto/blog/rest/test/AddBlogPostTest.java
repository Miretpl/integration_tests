package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AddBlogPostTest extends FunctionalTests {

    private static final String USER_API = "/blog/user/";
    private static final String POST_ADD_API = "/post";

    private static final Long CONFIRM_USER_ID = 1L;
    private static final Long NEW_USER_ID = 2L;
    private static final Long REMOVE_USER_ID = 3L;

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
               .post(USER_API + CONFIRM_USER_ID.toString() + POST_ADD_API);
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
               .post(USER_API + NEW_USER_ID.toString() + POST_ADD_API);
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
               .post(USER_API + REMOVE_USER_ID.toString() + POST_ADD_API);
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
               .post(USER_API + "111" + POST_ADD_API);
    }
}

package edu.iis.mto.blog.rest.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class FindUserTest extends FunctionalTests {

    @Test
    public void findOwnerUserByFullFirstNameShouldReturnOkStatusAndOwnerUserData() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("John"))
               .then()
               .body("[0].firstName", is("John"));
    }

    @Test
    public void findOwnerUserByFullLastNameShouldReturnOkStatusAndOwnerUserData() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("Steward"))
               .then()
               .body("[0].lastName", is("Steward"));
    }

    @Test
    public void findOwnerUserByFullEmailShouldReturnOkStatusAndOwnerUserData() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("john@domain.com"))
               .then()
               .body("[0].email", is("john@domain.com"));
    }

    @Test
    public void findOwnerUserByPartialFirstNameShouldReturnOkStatusAndOwnerUserData() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("Jo"))
               .then()
               .body("[0].firstName", is("John"));
    }

    @Test
    public void findOwnerUserByPartialLastNameShouldReturnOkStatusAndOwnerUserData() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("Ste"))
               .then()
               .body("[0].lastName", is("Steward"));
    }

    @Test
    public void findOwnerUserByPartialEmailShouldReturnOkStatusAndOwnerUserData() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("john@"))
               .then()
               .body("[0].email", is("john@domain.com"));
    }

    @Test
    public void findNotExistingUserByFirstNameShouldReturnOkStatusAndEmptyList() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("Dawid"))
               .then()
               .body("size()", is(0));
    }

    @Test
    public void findNotExistingUserByLastNameShouldReturnOkStatusAndEmptyList() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("Dawidowski"))
               .then()
               .body("size()", is(0));
    }

    @Test
    public void findNotExistingUserByEmailShouldReturnOkStatusAndEmptyList() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("dawid@domain.com"))
               .then()
               .body("size()", is(0));
    }

    @Test
    public void findRemovedUserByFirstNameShouldReturnOkStatusAndEmptyList() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("Alicja"))
               .then()
               .body("size()", is(0));
    }

    @Test
    public void findRemovedUserByLastNameShouldReturnOkStatusAndEmptyList() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("Alanowska"))
               .then()
               .body("size()", is(0));
    }

    @Test
    public void findRemovedUserByEmailShouldReturnOkStatusAndEmptyList() {
        given().accept(ContentType.JSON)
               .expect()
               .log()
               .all()
               .statusCode(HttpStatus.SC_OK)
               .when()
               .get(StaticElements.findUser("ala@domain.com"))
               .then()
               .body("size()", is(0));
    }
}

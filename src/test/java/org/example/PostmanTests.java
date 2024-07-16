package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanTests {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://postman-echo.com";
        given().headers("User-Agent", "PostmanRuntime/7.39.0",
                "Accept", "*/*", "Accept-Encoding", "gzip, deflate, br",
                "Connection", "keep-alive");
    }

    @Test
    public void testGet() {
        given()
                .when()
                .get("/get")
                .then().log().body()
                .body(
                        "args.foo1", equalTo("bar1"),
                        "args.foo2", equalTo("bar2"),
                        "url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }

    @Test
    public void testPostRaw() {
        given()
                .headers("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .post("/post")
                .then().log().body()
                .body("data", equalTo("This is expected to be sent back as part of response body."),
                        "headers.content-length", equalTo("58"),
                        "headers.content-type", equalTo("text/plain; charset=ISO-8859-1"),
                        "json", equalTo(null),
                        "url", equalTo("https://postman-echo.com/post"));;
    }

    @Test
    public void testPostFomr() {
        given()
                .headers("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1", "foo2", "bar2")
                .when()
                .post("/post")
                .then().log().body()
                .body("form.foo1", equalTo("bar1"),
                        "form.foo2", equalTo("bar2"),
                        "headers.content-length", equalTo("19"),
                        "headers.content-type", equalTo("application/x-www-form-urlencoded; charset=UTF-8"),
                        "json.foo1", equalTo("bar1"),
                        "json.foo2", equalTo("bar2"),
                        "url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    public void testPut() {
        given()
                .headers("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put("/put")
                .then().log().body()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."),
                        "headers.content-length", equalTo("58"),

                        "headers.content-type", equalTo("text/plain; charset=ISO-8859-1"),
                        "json", equalTo(null),
                        "url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    public void nestPatch() {
       given()
                .headers("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .patch("/patch")
                .then().log().body()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."),
                        "headers.content-length", equalTo("58"),
                        "headers.content-type", equalTo("text/plain; charset=ISO-8859-1"),
                        "json", equalTo(null),
                        "url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    public void testDelete() {
        given()
                .headers("Content-Type", "text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete("/delete")
                .then().log().body()
                .body(
                        "data", equalTo("This is expected to be sent back as part of response body."),
                        "headers.content-length", equalTo("58"),
                        "headers.content-type", equalTo("text/plain; charset=ISO-8859-1"),
                        "json", equalTo(null),
                        "url", equalTo("https://postman-echo.com/delete"));
    }
}

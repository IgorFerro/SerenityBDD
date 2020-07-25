package com.serenity.junit;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class FirstSerenityClass {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI="https://dog.ceo/api/breeds";
    }

    @Test
    public void getAll(){
        //RestAssured.given()
        SerenityRest.given()
                .when()
                .get("/image/random")
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}

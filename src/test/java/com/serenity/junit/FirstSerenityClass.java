package com.serenity.junit;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
    @Test
    public void getAllFail(){
        //RestAssured.given()
        SerenityRest.given()
                .when()
                .get("/image/random")
                .then()
                .log()
                .all()
                .statusCode(500);
    }
    @Pending
    @Test
    public  void pendingTest(){

    }
    @Ignore
    @Test
    public  void ignoreTest(){

    }
    @Test
    public  void ErrorTest(){
        System.out.println("This is a error test" + (5/0));

    }

    @Test
    public  void fileDoesNotExistTest() throws FileNotFoundException {
       File file = new File("E://file.txt");
        FileReader fr = new FileReader(file);
    }
    @Manual
    @Test
    public  void manualTest(){

    }


}

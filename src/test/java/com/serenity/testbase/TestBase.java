package com.serenity.testbase;

import io.restassured.RestAssured;

public class TestBase {

    public static void init(){
        //RestAssured.baseURI="http://localhost:8085/student";
        RestAssured.port=8085;
    }
}

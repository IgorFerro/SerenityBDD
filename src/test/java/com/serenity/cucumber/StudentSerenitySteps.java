package com.serenity.cucumber;

import com.serenityrestassured.model.StudentClass;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class StudentSerenitySteps {

    @Step("Creating student with firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,List<String>courses){
        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .when()
                .body(student)
                .post("http://localhost:8085/student")
                .then();
    }

    @Step("Getting the student information with firstName: {0}")
    public HashMap<String,Object> getStudentInfoByFirstName(String firstName){
        String p1 = "findAll{it.firstName=='";
        String p2= "'}.get(0)";

       return SerenityRest.given()
                .when()
                .get("http://localhost:8085/student/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1+firstName+p2);
    }

}

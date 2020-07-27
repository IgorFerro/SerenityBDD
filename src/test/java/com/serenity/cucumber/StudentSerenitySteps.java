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
    @Step("Updating student information with studentID: {0} firstName:{1}, lastName:{2}, email:{3}, programme:{4}, courses:{5}")
    public ValidatableResponse updateStudent(int studentId ,String firstName, String lastName, String email, String programme,List<String>courses){

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .put("http://localhost:8085/student/"+studentId)
                .then()
                .log()
                .all();
    }

    @Step("Deleting student information with ID: {0}")
    public void deleteStudent(int studentId){
        SerenityRest.rest().given().when().delete("http://localhost:8085/student/"+studentId);
    }

    @Step("Getting information of student with ID: {0}")
    public ValidatableResponse getStudentById(int studentId){
        return
                SerenityRest
                        .rest()
                        .given()
                        .when()
                        .get("http://localhost:8085/student/"+studentId).then();
    }

}

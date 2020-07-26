package com.serenity.junit.id.info;

import com.serenity.testbase.TestBase;
import com.serenity.utils.TestUtils;
import com.serenityrestassured.model.StudentClass;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(SerenityRunner.class)
public class StudentsCrudTest extends TestBase {

    static String firstName = "IGOR_USER"+TestUtils.getRandomValue();
    static String lastName = "IGOR_USER"+TestUtils.getRandomValue();
    static String programme = "ComputerScience";
    static String email = TestUtils.getRandomValue()+"IGOR_USER@email.com";


    @Title("This will create a new student")
    @Test
    public void createStudent(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C++");

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .post("http://localhost:8085/student")
                .then()
                .log()
                .all()
                .statusCode(201);

    }

}

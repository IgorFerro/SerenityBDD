package com.serenity.junit.id.info;

import com.serenity.testbase.TestBase;
import com.serenity.utils.TestUtils;
import com.serenityrestassured.model.StudentClass;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCrudTest extends TestBase {

    static String firstName = "IGOR_USER"+TestUtils.getRandomValue();
    static String lastName = "IGOR_USER"+TestUtils.getRandomValue();
    static String programme = "ComputerScience";
    static String email = TestUtils.getRandomValue()+"IGOR_USER@email.com";
    static int studentId;

    @Title("This will create a new student")
    @Test
    public void test001(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C++");

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        SerenityRest.rest().given()
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
    @Title("Verify if the student was added to the application")
    @Test
    public void test002(){

        String p1 = "findAll{it.firstName=='";
        String p2= "'}.get(0)";

        HashMap<String,Object> value = SerenityRest.given()
                .when()
                .get("http://localhost:8085/student/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1+firstName+p2);

        System.out.println("The value is: "+value);

        assertThat(value,hasValue(firstName));
        studentId = (int) value.get("id");
    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003(){

        String p1 = "findAll{it.firstName=='";
        String p2= "'}.get(0)";

        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C++");

        firstName=firstName+"_Updated";

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .put("http://localhost:8085/student/"+studentId)
                .then()
                .log()
                .all();

        HashMap<String,Object> value = SerenityRest.given()
                .when()
                .get("http://localhost:8085/student/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1+firstName+p2);

        System.out.println("The value is: "+value);

        assertThat(value,hasValue(firstName));
    }
    @Title("Delete the student an verify if student deleted!")
    @Test
    public void test004(){
        SerenityRest
                .rest()
                .given()
                .when()
                .delete("http://localhost:8085/student/"+studentId);

        SerenityRest
                .rest()
                .given()
                .when()
                .get("http://localhost:8085/student/"+studentId).then()
                .log()
                .all()
                .statusCode(404);


    }

}

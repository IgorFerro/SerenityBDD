package com.serenity.junit.id.info;

import com.serenity.cucumber.StudentSerenitySteps;
import com.serenity.testbase.TestBase;
import com.serenity.utils.TestUtils;
import com.serenityrestassured.model.StudentClass;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
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


    @Steps
    StudentSerenitySteps steps;

    @Title("This will create a new student")
    @Test
    public void test001(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C++");

        steps.createStudent(firstName, lastName, email, programme, courses).statusCode(201);



    }
    @Title("Verify if the student was added to the application")
    @Test
    public void test002(){

        HashMap<String,Object> value = steps.getStudentInfoByFirstName(firstName);
        assertThat(value,hasValue(firstName));
        studentId = (int) value.get("id");
    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003(){

        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C++");

        firstName=firstName+"_Updated";
        steps.updateStudent(studentId, firstName,lastName,email,programme, courses);


        HashMap<String,Object> value = steps.getStudentInfoByFirstName(firstName);
        assertThat(value,hasValue(firstName));
    }
    @Title("Delete the student an verify if student deleted!")
    @Test
    public void test004(){
        steps.deleteStudent(studentId);
        steps.getStudentById(studentId).statusCode(404);
    }

}

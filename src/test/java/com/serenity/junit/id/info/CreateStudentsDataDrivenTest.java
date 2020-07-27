package com.serenity.junit.id.info;

import com.serenity.cucumber.StudentSerenitySteps;
import com.serenity.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@UseTestDataFrom("testdata/studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentsDataDrivenTest extends TestBase {

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public StudentSerenitySteps getSteps() {
        return steps;
    }

    public void setSteps(StudentSerenitySteps steps) {
        this.steps = steps;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course;

    @Steps
    StudentSerenitySteps steps;

    @Title("DataDriven Test for adding multiple students to the Student App.")
    @Test
    public void createMultiplesStudents(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add(course);

        steps.createStudent(firstName,lastName,email,programme,courses)
                .statusCode(201);
    }
}

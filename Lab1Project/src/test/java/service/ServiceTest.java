package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;
import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceTest {
    Service service;

    @BeforeAll
    void createService() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @org.junit.jupiter.api.Test
    void saveStudent() {
        int returnValue = service.saveStudent("50","Dani",533);
        service.deleteStudent("50");
        assertEquals(1,returnValue);
    }

    @org.junit.jupiter.api.Test
    void saveHomework() {
        int returnValue = service.saveHomework("7","java",10,0);
        service.deleteHomework("7");
        assertTrue(returnValue == 1);
    }

    @org.junit.jupiter.api.Test
    void saveGrade() {
        int returnValue = service.saveGrade("50","7",8.5,10,"gg");
        assertNotEquals(0,returnValue);
    }


    @ParameterizedTest
    @ValueSource(strings = {"2","50"})
    void deleteStudent(String id) {
        service.saveStudent("2","Dani",533);
        service.saveStudent("50","Jani",533);
        int returnValue = service.deleteStudent(id);
        assertTrue(returnValue == 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","2"})
    void deleteHomework(String id) {
        service.saveHomework("1","java",10,0);
        service.saveHomework("2","java",10,0);
        int returnValue = service.deleteHomework(id);
        assertTrue(returnValue == 1);
    }
}
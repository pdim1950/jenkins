package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ServiceMockTest {
    @Mock
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


    @Test
    void saveStudent() {
        assertNotNull(service);
        Mockito.when(service.saveStudent(anyString(),anyString(),anyInt())).thenReturn(1);
        assertEquals(1,service.saveStudent("50","Dani",533));
    }

    @Test
    void deleteStudent() {
        assertNotNull(service);
        Mockito.when(service.deleteStudent(anyString())).thenReturn(1);
        assertEquals(1,service.deleteStudent("50"));
        Mockito.verify(service,times(1)).deleteStudent(anyString());
    }

    @Test
    void saveHomework() {
        assertNotNull(service);
        Mockito.when(service.saveHomework(anyString(),anyString(),anyInt(),anyInt())).thenReturn(1);
        Mockito.verify(service,times(0)).saveHomework(anyString(),anyString(),anyInt(),anyInt());
        assertEquals(1,service.saveHomework("7","java",10,0));
        Mockito.verify(service).saveHomework(anyString(),anyString(),anyInt(),anyInt());
    }
}
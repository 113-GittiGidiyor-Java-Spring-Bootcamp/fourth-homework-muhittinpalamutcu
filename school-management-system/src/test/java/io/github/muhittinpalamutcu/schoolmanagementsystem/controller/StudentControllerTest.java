package io.github.muhittinpalamutcu.schoolmanagementsystem.controller;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.StudentDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    StudentService mockStudentService;

    @InjectMocks
    StudentController studentController;

    @Test
    void saveStudent() {
        // given
        Student student = new Student();
        Optional<Student> expected = Optional.of(student);
        when(mockStudentService.save(any())).thenReturn(expected);

        // when
        StudentDTO dto = new StudentDTO();
        Student actual = this.studentController.saveStudent(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(), actual)
        );
    }

    @Test
    void saveCustomer2() {
        // given
        when(mockStudentService.save(any())).thenReturn(Optional.empty());

        // when
        StudentDTO dto = new StudentDTO();
        ResponseEntity<Student> actual = this.studentController.saveStudent(dto);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }
}
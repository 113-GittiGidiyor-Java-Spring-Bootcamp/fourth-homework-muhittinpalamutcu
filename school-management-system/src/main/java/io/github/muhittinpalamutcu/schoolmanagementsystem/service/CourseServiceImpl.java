package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.CourseDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.BadRequestException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.CourseIsAlreadyExistException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.StudentNumberForOneCourseExceededException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.CourseMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.CourseRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no course with id: " + id);
        }
        return courseRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Optional<Course> save(CourseDTO courseDTO) {
        if (courseRepository.findByCourseCode(courseDTO.getCourseCode()) != null) {
            throw new CourseIsAlreadyExistException("Course with code " + courseDTO.getCourseCode() + " is already exists!");
        }

        if (isExists(courseDTO.getId())) {
            throw new CourseIsAlreadyExistException("Course with id " + courseDTO.getId() + " is already exists!");
        }

        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return Optional.of(courseRepository.save(course));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no course with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Course update(CourseDTO courseDTO) {
        if (!isExists(courseDTO.getId())) {
            throw new BadRequestException("There is no course with id " + courseDTO.getId());
        }
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return courseRepository.save(course);
    }

    @Override
    public Course findByName(String name) {
        Course course = courseRepository.findByName(name);
        if (course == null) {
            throw new BadRequestException("There is no course with name: " + name);
        }
        return courseRepository.findByName(name);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Course course = courseRepository.findByName(name);
        if (course == null) {
            throw new BadRequestException("There is no course with name: " + name);
        }
        courseRepository.deleteCourseByName(name);
    }

    @Transactional
    public Optional<Course> registerStudentToCourse(String courseCode, int studentId) {
        Course course = courseRepository.findByCourseCode(courseCode);
        if (course == null) {
            throw new BadRequestException("There is no course with code: " + courseCode);
        }

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (!optionalStudent.isPresent()) {
            throw new BadRequestException("There is no student with id: " + studentId);
        }
        Student student = optionalStudent.get();

        List<Student> students = course.getStudents();
        if (students.size() < 20) {
            students.add(student);
            course.setStudents(students);
            return Optional.of(courseRepository.save(course));
        } else {
            throw new StudentNumberForOneCourseExceededException("Students number can not exceed 20 - current students number enrolled in course: " + students.size());
        }
    }

    public Optional<Course> registerInstructorToCourse(String courseCode, int instructorId) {
        Course course = courseRepository.findByCourseCode(courseCode);
        if (course == null) {
            throw new BadRequestException("There is no course with code: " + courseCode);
        }

        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
        if (!optionalInstructor.isPresent()) {
            throw new BadRequestException("There is no instructor with id: " + instructorId);
        }

        Instructor instructor = optionalInstructor.get();
        course.setInstructor(instructor);
        return Optional.of(courseRepository.save(course));
    }

    public boolean isExists(int id) {
        return courseRepository.existsById(id);
    }


}

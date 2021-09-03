package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<Course> {
    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id).get();
    }

    /*
    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }
     */

    @Override
    @Transactional
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

    /*
    @Override
    @Transactional
    public Course update(Course course) {
        return courseRepository.save(course);
    }*/

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        courseRepository.deleteCourseByName(name);
    }
}

package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.StudentDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.BadRequestException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.StudentMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Optional<Student> save(StudentDTO studentDTO) {
        boolean isExists = studentRepository.existsById(studentDTO.getId());

        if (isExists) {
            throw new BadRequestException("Student with id " + studentDTO.getId() + " is already exists!");
        }

        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        return Optional.of(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        boolean isExists = studentRepository.existsById(id);

        if (!isExists) {
            throw new BadRequestException("There is no student with id " + id);
        }

        studentRepository.deleteById(id) ;
    }

    @Override
    @Transactional
    public Student update(StudentDTO studentDTO) {
        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        return studentRepository.save(student);
    }

    @Override
    public Student findByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<?> getGenderWithGrouping() {
        return studentRepository.getGenderWithGrouping();
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        studentRepository.deleteStudentByName(name);
    }
}

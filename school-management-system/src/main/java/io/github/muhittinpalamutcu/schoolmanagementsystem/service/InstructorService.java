package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService implements BaseService<Instructor> {

    private final InstructorRepository instructorRepository;

    @Override
    public List<Instructor> findAll() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    @Override
    public Instructor findById(int id) {
        return instructorRepository.findById(id).get();
    }

    /*
    @Override
    @Transactional
    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }*/

    @Override
    @Transactional
    public void deleteById(int id) {
        instructorRepository.deleteById(id);
    }

    /*
    @Override
    @Transactional
    public Instructor update(Instructor instructor) {
        return instructorRepository.save(instructor);
    }*/

    @Override
    public Instructor findByName(String name) {
        return instructorRepository.findByName(name);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        instructorRepository.deleteInstructorByName(name);
    }

    public List<Instructor> find3HighestPaidPermanentInstructor() {
        return instructorRepository.find3HighestPaidPermanentInstructor(PageRequest.of(0, 3));
    }

    public List<Instructor> find3LeastPaidPermanentInstructor() {
        return instructorRepository.find3LeastPaidPermanentInstructor(PageRequest.of(0, 3));
    }
}

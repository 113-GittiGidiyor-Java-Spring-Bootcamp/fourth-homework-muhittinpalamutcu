package io.github.muhittinpalamutcu.schoolmanagementsystem.mappers;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.InstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {
    Instructor mapFromInstructorDTOtoInstructor(InstructorDTO instructorDTO);
    InstructorDTO mapFromInstructorToInstructorDTO(Instructor instructor);
}

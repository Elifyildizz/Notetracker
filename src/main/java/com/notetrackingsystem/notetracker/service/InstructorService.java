package com.notetrackingsystem.notetracker.service;

import com.notetrackingsystem.notetracker.model.Instructor;
import com.notetrackingsystem.notetracker.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor saveInstructor(Instructor instructor) {
        List<Instructor> newRecordInstructor = instructorRepository.findAll();
        boolean isSame = newRecordInstructor.stream()
                .anyMatch(a -> a.getName().equals(instructor.getName()) && instructor.getSurname().equals(instructor.getSurname()));
        if (isSame) {
            throw new RuntimeException("This instructor has been already enrolled.");
        }
        instructorRepository.save(instructor);
        return instructor;
    }

    public String deleteInstructor(long instructorID) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorID);
        if (optionalInstructor.isPresent()) {
            instructorRepository.deleteById(optionalInstructor.get().getInstructorID());
        } else {
            throw new RuntimeException(instructorID + " Is not found.");
        }
        return null;
    }

    public Instructor getInstructorById(long instructorID) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorID);
        if (optionalInstructor.isPresent()) {
            return optionalInstructor.get();
        } else {
            throw new RuntimeException(instructorID + " Is not found.");
        }
    }

    public String updateInstructor(Instructor instructor) {
        long instructorID = instructor.getInstructorID();
        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorID);

        optionalInstructor.ifPresent(existingInstructor -> {
            existingInstructor.setName(instructor.getName());
            existingInstructor.setSurname(instructor.getSurname());
            instructorRepository.save(existingInstructor);
        });
        return "Instructor updated successfully.";
    }
}

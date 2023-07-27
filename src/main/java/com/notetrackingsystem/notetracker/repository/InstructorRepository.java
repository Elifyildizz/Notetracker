package com.notetrackingsystem.notetracker.repository;

import com.notetrackingsystem.notetracker.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}

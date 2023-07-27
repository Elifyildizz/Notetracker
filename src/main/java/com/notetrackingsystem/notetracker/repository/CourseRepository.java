package com.notetrackingsystem.notetracker.repository;

import com.notetrackingsystem.notetracker.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

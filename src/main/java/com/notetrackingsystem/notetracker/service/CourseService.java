package com.notetrackingsystem.notetracker.service;

import com.notetrackingsystem.notetracker.model.Course;
import com.notetrackingsystem.notetracker.model.Student;
import com.notetrackingsystem.notetracker.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course) {
        List<Course> newRecordCourse = courseRepository.findAll();
        boolean isSame = newRecordCourse.stream()
                .anyMatch(a -> a.getCourseName().equals(course.getCourseName()));
        if (isSame) {
            throw new RuntimeException("This course has been already enrolled.");
        }
        courseRepository.save(course);
        return course;
    }

    public String deleteCourse(long courseID) {
        Optional<Course> optionalCourse = courseRepository.findById(courseID);
        if (optionalCourse.isPresent()) {
            courseRepository.deleteById(optionalCourse.get().getCourseID());
        } else {
            throw new RuntimeException(courseID + " Is not found.");
        }
        return null;
    }

    public Course getCourseById(long courseID) {
        Optional<Course> optionalCourse = courseRepository.findById(courseID);
        Course course;
        if (optionalCourse.isPresent()) {
            course = optionalCourse.get();
        } else {
            throw new RuntimeException(courseID + " Is not found.");
        }
        return course;
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public List<Student> getPassedStudentsByCourseId(long courseID) {
        Optional<Course> optionalCourse = courseRepository.findById(courseID);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            List<Student> studentList = course.getStudentID();

            return studentList.stream()
                    .filter(student -> student.getGrade() >= 50)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public List<Student> getFailedStudentsByCourseId(long courseID) {
        Optional<Course> optionalCourse = courseRepository.findById(courseID);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            List<Student> studentList = course.getStudentID();

            return studentList.stream()
                    .filter(student -> student.getGrade() < 50)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public float getCourseAverageGrade(long courseID) {
        Optional<Course> optionalCourse = courseRepository.findById(courseID);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            List<Student> students = course.getStudentID();

            float sum = 0;
            int studentCount = students.size();
            for (Student student : students) {
                sum = sum + student.getGrade();
            }
            return sum / studentCount;
        } else {
            throw new RuntimeException(courseID + " Course is not found.");
        }
    }
}

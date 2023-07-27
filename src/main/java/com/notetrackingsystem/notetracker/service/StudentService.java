package com.notetrackingsystem.notetracker.service;

import com.notetrackingsystem.notetracker.model.Course;
import com.notetrackingsystem.notetracker.model.Student;
import com.notetrackingsystem.notetracker.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        List<Student> newRecordStudent = studentRepository.findAll();

        boolean isSame = newRecordStudent.stream()
                .anyMatch(a -> a.getName().equals(student.getName()) && student.getSurname().equals(student.getSurname()));
        if (isSame) {
            throw new RuntimeException("This student has been already enrolled.");
        }
        studentRepository.save(student);
        return student;
    }

    public String deleteStudent(long studentID) {
        Optional<Student> optionalStudent = studentRepository.findById(studentID);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(optionalStudent.get().getStudentID());
        } else {
            throw new RuntimeException(studentID + " Is not found.");
        }
        return null;
    }

    public Student getStudentById(long studentID) {
        Optional<Student> optionalStudent = studentRepository.findById(studentID);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new RuntimeException(studentID + " Is not found.");
        }
    }

    public String updateStudent(Student student) {
        long studentId = student.getStudentID();
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        optionalStudent.ifPresent(existingStudent -> {
            existingStudent.setName(student.getName());
            existingStudent.setSurname(student.getSurname());
            existingStudent.setGrade(student.getGrade());
            existingStudent.setAverageGrade(student.getAverageGrade());
            studentRepository.save(existingStudent);
        });
        return "Student updated successfully.";
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public float calculateAverageNote(long studentID) {
        Optional<Student> optionalStudent = studentRepository.findById(studentID);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Float> grades = new ArrayList<>();
            grades.add(student.getGrade());

            Course course = (Course) student.getCourseID();
            List<Student> studentsEnrolledCourse = course.getStudents();
            for (Student s : studentsEnrolledCourse) {
                grades.add(s.getGrade());
            }
            float sum = 0;
            for (Float grade : grades) {
                sum = sum + grade;
            }
            return sum / grades.size();
        } else {
            throw new RuntimeException(studentID + " Is not found.");
        }
    }
}

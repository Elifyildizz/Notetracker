package com.notetrackingsystem.notetracker.controller;

import com.notetrackingsystem.notetracker.model.Student;
import com.notetrackingsystem.notetracker.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/saveStudent")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/delete/{studentID}")
    public String deleteStudent(@PathVariable(value = "studentID") long studentID) {
        return studentService.deleteStudent(studentID);
    }

    @GetMapping("/getStudentById/{studentID}")
    public Student getStudentById(@PathVariable long studentID) {
        return studentService.getStudentById((studentID));
    }

    @PutMapping("/updateStudent")
    public String updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/calculateAverageNote/{studentID}")
    public float calculateAverageNote(@PathVariable long studentID) {
        return studentService.calculateAverageNote(studentID);
    }
}
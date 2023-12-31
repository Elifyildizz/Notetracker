package com.notetrackingsystem.notetracker.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "course")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseID")
    private int courseID;
    @Column(name = "courseName", nullable = false)
    private String courseName;
    @ManyToMany
    @JoinColumn(name = "studentID")
    private List<Student> studentID;
    @ManyToMany
    @JoinColumn(name = "instructorID")
    private List<Instructor> instructorID;

    @ElementCollection
    @CollectionTable(name = "courseStudentGrades", joinColumns = @JoinColumn(name = "courseID"))
    @MapKeyJoinColumn(name = "studentID")
    @Column(name = "grade")
    private Map<Student, Float> studentGrades;

    public Course() {
    }

    public Course(String courseName, List<Student> studentID, List<Instructor> instructorID, Map<Student, Float> studentGrades) {
        this.courseName = courseName;
        this.studentID = studentID;
        this.instructorID = instructorID;
        this.studentGrades = studentGrades;
    }

    public long getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public Map<Student, Float> getStudentGrades() {
        return studentGrades;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getStudentID() {
        return studentID;
    }

    public void setStudentID(List<Student> studentID) {
        this.studentID = studentID;
    }

    public List<Instructor> getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(List<Instructor> instructorID) {
        this.instructorID = instructorID;
    }

    public void setStudentGrades(Map<Student, Float> studentGrades) {
        this.studentGrades = studentGrades;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", studentID=" + studentID +
                ", instructorID=" + instructorID +
                ", studentGrades=" + studentGrades +
                '}';
    }
}
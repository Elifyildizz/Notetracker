package com.notetrackingsystem.notetracker.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentID;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;
    @Column(name = "grade", nullable = false)
    private float grade;
    @Column(name = "averageGrade", nullable = false)
    private float averageGrade;
    @OneToMany
    @JoinColumn(name = "courseID")
    private List<Course> courseID;

    public Student() {
    }

    public Student(long studentID, String name, String surname, float grade, float averageGrade, List<Course> courseID) {
        this.studentID = studentID;
        this.name = name;
        this.surname = surname;
        this.grade = grade;
        this.averageGrade = averageGrade;
        this.courseID = courseID;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public List<Course> getCourseID() {
        return courseID;
    }

    public void setCourseID(List<Course> courseID) {
        this.courseID = courseID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID == student.studentID && Float.compare(student.grade, grade) == 0 && Float.compare(student.averageGrade, averageGrade) == 0 && Objects.equals(name, student.name) && Objects.equals(surname, student.surname) && Objects.equals(courseID, student.courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, name, surname, grade, averageGrade, courseID);
    }
}
package com.notetrackingsystem.notetracker.model;

import jakarta.persistence.*;

import java.util.Arrays;
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
    @Column(name = "grade1", nullable = false)
    private double grade1;
    @Column(name = "grade2", nullable = false)
    private double grade2;
    @Column(name = "grade3", nullable = false)
    private double grade3;
    @Column(name = "gpa", nullable = false)
    private double gpa;


    @OneToMany
    @JoinColumn(name = "courseID")
    private List<Course> courseID;

    public Student() {
    }

    public Student(String name, String surname, double grade1, double grade2, double grade3) {
        this.name = name;
        this.surname = surname;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    public Student(long studentID, String name, String surname, double grade1, double grade2, double grade3, double gpa) {
        this.studentID = studentID;
        this.name = name;
        this.surname = surname;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.gpa = gpa;
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

    public double getGrade1() {
        return grade1;
    }

    public double getGrade2() {
        return grade2;
    }

    public double getGrade3() {
        return grade3;
    }

    public double calculateGPA() {
        return (grade1 + grade2 + grade3) / 3.0;
    }

    public void setGrade(double grade1) {
        this.grade1 = grade1;
        this.gpa = calculateGPA();
    }

    public void setGrade(double grade1, double grade2) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.gpa = calculateGPA();
    }

    public void setGrade(double grade1, double grade2, double grade3) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.gpa = calculateGPA();
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
        return studentID == student.studentID && Double.compare(student.grade1, grade1) == 0 && Double.compare(student.grade2, grade2) == 0 && Double.compare(student.grade3, grade3) == 0 && Double.compare(student.gpa, gpa) == 0 && Objects.equals(name, student.name) && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, name, surname, grade1, grade2, grade3, gpa);
    }
}
package com.notetrackingsystem.notetracker;

import com.notetrackingsystem.notetracker.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class NotetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotetrackerApplication.class, args);
	}
	@GetMapping
	public List<Student>hello() {
		return List.of(
				new Student(2,"Elif","Yildiz",90,80,70,80
				)
		);
	}
}

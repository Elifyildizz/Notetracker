package com.notetrackingsystem.notetracker.controller;

import com.notetrackingsystem.notetracker.model.Instructor;
import com.notetrackingsystem.notetracker.service.InstructorService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;


    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/saveInstructor")
    public Instructor instructor(@RequestBody Instructor instructor) {
        return instructorService.saveInstructor(instructor);
    }

    @DeleteMapping("/delete/{instructorID}")
    public String deleteInstructor(@PathVariable(value = "instructorID") long instructorID) {
        return instructorService.deleteInstructor(instructorID);
    }

    @GetMapping("/getInstructorById/{id}")
    public Instructor getInstructorById(@PathVariable long instructorID) {
        return instructorService.getInstructorById((instructorID));
    }

    @PutMapping("/updateInstructor")
    public String updateInstructor(@RequestBody Instructor instructor) {
        return instructorService.updateInstructor(instructor);
    }

}
package com.example.july2023.controller;

import com.example.july2023.model.Courses;
import com.example.july2023.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    CoursesService cService;

    @GetMapping("/all")
    public List<Courses> getAllCourses(){
        return cService.getAllCourses();
    }

    @PostMapping("/add")
    public void addCourse(@RequestBody Courses course){
        cService.addCourse(course);
    }
}

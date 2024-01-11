package com.luchiari.crudspring.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import com.luchiari.crudspring.model.Course;
import com.luchiari.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController //Fala para o Spring que essa classe contém um endpoint, que será acessado via API - É um Java Servlet por trás dos panos
@RequestMapping("/api/courses")
@AllArgsConstructor // Gera automaticamenteo constructor da classe
public class CourseController {

    //@Autowired - Poderia instaciar com esta notacao
    private final CourseRepository courseRepository;//Boa pratica deixar como final
    
    @GetMapping()
    public List<Course> list() {
        // List<Course> courses = new ArrayList<>();
        // courseRepository.findAll().forEach(courses::add);

        // return courses;
        return courseRepository.findAll();
    }
}

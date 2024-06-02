package com.luchiari.crudspring.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import com.luchiari.crudspring.dto.CourseDTO;
import com.luchiari.crudspring.model.Course;
import com.luchiari.crudspring.repository.CourseRepository;
import com.luchiari.crudspring.service.CourseService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController //Fala para o Spring que essa classe contém um endpoint, que será acessado via API - É um Java Servlet por trás dos panos
@RequestMapping("/api/courses")
public class CourseController {

    //@Autowired - Poderia instaciar com esta notacao
    private final CourseService courseService;//Boa pratica deixar como final

    public CourseController(
        CourseService courseService
    ) {
        this.courseService = courseService;
    }
    
    //@RequestMapping( method=RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<CourseDTO> list() {
        return courseService.list();
    }
    
    @GetMapping("/{id}")
    public CourseDTO findById( @PathVariable @NotNull @Positive Long id ) {
        return courseService.findById(id);
    }

    //@RequestMapping( method=RequestMethod.POST)
    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    //Pode ser usado tanto a notacao ResponseStatus quanto a funcao no return
    //Porem utilizando a funcao e possivel manipular mais informacoes do retorno
    public CourseDTO create( @RequestBody @Valid CourseDTO course ) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public CourseDTO update(
        @PathVariable @NotNull @Positive Long id, 
        @RequestBody @Valid CourseDTO course
    ) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete( @PathVariable @NotNull @Positive Long id ) {
        courseService.delete(id);
    }
}

package com.luchiari.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luchiari.crudspring.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
// public interface CourseRepository extends CrudRepository<Course, Long> {
    
}

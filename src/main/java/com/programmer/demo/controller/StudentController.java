package com.programmer.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmer.demo.model.Student;
import com.programmer.demo.repo.Repo;

@RestController
public class StudentController {
	
	@Autowired
	Repo repo;
	@PostMapping("/students")
	public ResponseEntity<Student> addStudents(@RequestBody Student student) {
		
		
		return new ResponseEntity<>(repo.save(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/students")
	public  ResponseEntity<List<Student>> getStudents() {
		
		return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
		
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id) {
		
		Optional<Student> student= repo.findById(id); 
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		 
		
	}
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
		Optional<Student> student= repo.findById(id); 
		if(student.isPresent()) {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	  
	  
		
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> putStudent(@PathVariable int id, @RequestBody Student stud) {
		
		Optional<Student> student= repo.findById(id); 
		if(student.isPresent()) {
			student.get().setName(stud.getName());
			student.get().setEmail(stud.getEmail());
			return new ResponseEntity<>(repo.save(student.get()),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		 
		
	}
	

}

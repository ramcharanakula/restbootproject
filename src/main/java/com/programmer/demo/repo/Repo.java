package com.programmer.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmer.demo.model.Student;

public interface Repo extends JpaRepository<Student, Integer> {

}

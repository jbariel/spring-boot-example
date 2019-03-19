/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.jbariel.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jbariel.example.springboot.models.Student;
import com.jbariel.example.springboot.persistance.StudentRepository;

/**
 * Simple CRUD student controller
 * 
 * @author Jarrett Bariel
 *
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    /**
     * Wire in access to JPA assets
     */
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Default construtor.
     *
     */
    public StudentController() {
        super();
    }

    /**
     * @return list of all Students
     *
     */
    @GetMapping("/")
    @ResponseBody
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    /**
     * @param id
     * @return Student that was found, {@code null} if no student matches that id.
     *
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Student getById(@PathVariable("id") final long id) {
        return loadById(id);
    }

    /**
     * @param student
     *            object to save
     * @return newly created object.
     *
     */
    @PutMapping("/")
    @ResponseBody
    public Student create(@RequestBody final Student student) {
        return studentRepository.save(student);
    }

    /**
     * @param id
     *            of Student to update
     * @param student
     *            Student parameters to update, can be only a partial set of parameters
     * @return updated Student, or {@code null} if no student was found
     *
     */
    @PostMapping("/{id}")
    @ResponseBody
    public Student updateById(@PathVariable("id") final long id, @RequestBody final Student student) {
        Student dbStudent = loadById(id);
        return (null == student) ? null : studentRepository.save(dbStudent.updateFrom(student));
    }

    /**
     * @param id
     *            of student to delete
     * @return deleted student, or {@code null} if no student found.
     *
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Student deleteById(@PathVariable("id") final long id) {
        Student dbStudent = loadById(id);
        studentRepository.delete(dbStudent);
        return dbStudent;
    }

    /**
     * Keep the code DRY
     * 
     * @param id
     *            to load
     * @return Student or {@code null} if no student exists
     *
     */
    private Student loadById(final long id) {
        return studentRepository.findById(id).orElse(null);
    }
}

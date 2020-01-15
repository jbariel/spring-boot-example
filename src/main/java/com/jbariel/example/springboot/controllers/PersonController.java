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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jbariel.example.springboot.models.Person;
import com.jbariel.example.springboot.persistance.PersonRepository;

/**
 * Abstract all the CRUD and keep the generic chain intact
 * 
 * @author Jarrett Bariel
 * 
 * @param <R> correct implementation {@link PersonRepository} type
 * @param <T> correct object type that extends {@link Person}
 *
 */
@RestController
public abstract class PersonController<R extends PersonRepository<T>, T extends Person<T>> {

	/**
	 * Default construtor.
	 *
	 */
	public PersonController() {
		super();
	}

	/**
	 * @return the appropriate {@link PersonRepository} that is autowired
	 *
	 */
	protected abstract R repo();

	/**
	 * @return list of all {@code T} objects
	 *
	 */
	@GetMapping("/")
	@ResponseBody
	public List<T> getAll() {
		return repo().findAll();
	}

	/**
	 * @param id to load
	 * @return {@code T} that was found, {@code null} if no {@code T} matches that
	 *         id.
	 *
	 */
	@GetMapping("/{id}")
	@ResponseBody
	public T getById(@PathVariable("id") final long id) {
		return loadById(id);
	}

	/**
	 * @param obj {@code T} to save
	 * @return newly created {@code T}.
	 *
	 */
	@PutMapping("/")
	@ResponseBody
	public T create(@RequestBody final T obj) {
		return repo().save(obj);
	}

	/**
	 * @param id  of {@code T} to update
	 * @param obj {@code T} parameters to update, can be only a partial set of
	 *            parameters
	 * @return updated {@code T}, or {@code null} if no {@code T} was found
	 *
	 */
	@PostMapping("/{id}")
	@ResponseBody
	public T updateById(@PathVariable("id") final long id, @RequestBody final T obj) {
		T dbObj = loadById(id);
		return (null == obj) ? null : repo().save(dbObj.updateFrom(obj));
	}

	/**
	 * @param id of {@code T} to delete
	 * @return deleted {@code T}, or {@code null} if no {@code T} found.
	 *
	 */
	@DeleteMapping("/{id}")
	@ResponseBody
	public T deleteById(@PathVariable("id") final long id) {
		T dbObj = loadById(id);
		repo().delete(dbObj);
		return dbObj;
	}

	/**
	 * Keep the code DRY
	 * 
	 * @param id to load
	 * @return {@code T} or {@code null} if no such object with that id exists
	 *
	 */
	private T loadById(final long id) {
		return repo().findById(id).orElse(null);
	}

}

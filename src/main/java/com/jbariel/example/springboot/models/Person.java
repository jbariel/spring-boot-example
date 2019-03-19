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
package com.jbariel.example.springboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.h2.util.StringUtils;

/**
 * Abstract management of the concept of a person
 * 
 * @param <S>
 *            self reference to use builder patterns
 * @author Jarrett Bariel
 *
 */
@Entity
public abstract class Person<S extends Person<S>> {

    /**
     * Primary key as a long
     */
    @Id
    @GeneratedValue
    protected long id;

    /**
     * Stores the first name for each implementation
     */
    protected String firstName;

    /**
     * Stores the last name for each implementation
     */
    protected String lastName;

    /**
     * Default construtor.
     *
     */
    public Person() {
        super();
    }

    /**
     * @return cast version of {@code this}
     *
     */
    @SuppressWarnings("unchecked")
    protected S me() {
        return (S) this;
    }

    /**
     * @param id
     *            to set as part of the builder pattern
     * @return self
     *
     */
    public S withId(final long id) {
        setId(id);
        return me();
    }

    /**
     * @param firstName
     *            to set as part of the builder pattern
     * @return self
     *
     */
    public S withFirstName(final String firstName) {
        setFirstName(firstName);
        return me();
    }

    /**
     * @param lastName
     *            to set as part of the builder pattern
     * @return self
     *
     */
    public S withLastName(final String lastName) {
        setLastName(lastName);
        return me();
    }

    /**
     * @return the id
     *
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     *
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     *
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     *
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     *
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Requires compliance of all subclasses.
     * 
     * @param obj
     *            of type {@code S} that we should update the fields in {@code this} object from
     * @return {@link #me()}
     *
     */
    protected abstract S doUpdateFrom(S obj);

    /**
     * @param obj
     *            of type {@code S} that we should update the fields in {@code this} object from.
     * @return {@link #me()}
     *
     */
    public S updateFrom(final S obj) {
        if (0 < obj.getId())
            setId(obj.getId());
        if (!StringUtils.isNullOrEmpty(obj.getFirstName()))
            setFirstName(obj.getFirstName());
        if (!StringUtils.isNullOrEmpty(obj.getLastName()))
            setLastName(obj.getLastName());
        return doUpdateFrom(obj);
    }

}

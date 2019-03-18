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

/**
 * Abstract management of the concept of a person
 * 
 * @param <S>
 *            self reference to use builder patterns
 * @author Jarrett Bariel
 *
 */
public abstract class Person<S extends Person<S>> {

    /**
     * Primary key as a long
     */
    // @Id
    // @Generated
    private long id;

    /**
     * Stores the first name for each implementation
     */
    private String firstName;

    /**
     * Stores the last name for each implementation
     */
    private String lastName;

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

}

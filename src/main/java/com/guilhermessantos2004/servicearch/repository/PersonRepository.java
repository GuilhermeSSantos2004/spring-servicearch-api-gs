package com.guilhermessantos2004.servicearch.repository;

import com.guilhermessantos2004.servicearch.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}

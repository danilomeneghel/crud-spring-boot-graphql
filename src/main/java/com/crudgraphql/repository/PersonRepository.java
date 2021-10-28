package com.crudgraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudgraphql.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
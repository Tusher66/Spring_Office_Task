package com.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registration.model.Engineers;



public interface EngineersRepository extends JpaRepository<Engineers, Integer> {

}

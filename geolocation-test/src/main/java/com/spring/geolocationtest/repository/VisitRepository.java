package com.spring.geolocationtest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.geolocationtest.model.Visits;



public interface VisitRepository extends JpaRepository<Visits, Long> {
	   Optional<Visits> findByCity(String city);


}

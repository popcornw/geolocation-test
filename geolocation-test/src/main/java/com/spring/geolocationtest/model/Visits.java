package com.spring.geolocationtest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long visitId;
	private String city ;
	private Integer visitCount = 0 ;
	
}

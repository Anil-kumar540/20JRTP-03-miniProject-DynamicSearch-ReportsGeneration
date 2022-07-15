package com.poc.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poc.search.entity.EligibilityDetails;

public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails, Integer>{

	@Query("select distinct(planName) from EligibilityDetails")
	public List<String> getUniquePlanNames();
	
	@Query("Select distinct(planStatus) from EligibilityDetails")
	public List<String> gatUniquePlanStatuses();
	
	
	
}

package com.poc.search.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchForm {
	
	private String  planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	
}

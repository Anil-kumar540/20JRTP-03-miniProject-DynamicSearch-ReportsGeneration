package com.poc.search.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.poc.search.bindings.SearchForm;
import com.poc.search.bindings.SearchResponse;


public interface ApplicationService {
	
	public List<String> loadPlanNames();
	public List<String> loadPlanStatuses();
	public List<SearchResponse> searchPlans(SearchForm searchForm);
//	public void exportExcel(List<SearchResponse> records);
//	public void exportPdf(List<SearchResponse> records);
	public void exportExcelSheet(List<SearchResponse> response, HttpServletResponse httpResponse) throws Exception;
	
	public void exportPdf(List<SearchResponse> response, HttpServletResponse httpResponse) throws Exception;

	

}

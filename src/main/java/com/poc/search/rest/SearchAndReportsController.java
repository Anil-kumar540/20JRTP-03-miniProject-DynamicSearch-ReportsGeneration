package com.poc.search.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.search.bindings.SearchForm;
import com.poc.search.bindings.SearchResponse;
import com.poc.search.service.ApplicationService;

@RestController
public class SearchAndReportsController {
	
	@Autowired
	private ApplicationService service;
	
	@GetMapping("/plan-names")
	public List<String> getPlans(){
		
		return service.loadPlanNames();
	}
	
	@GetMapping("/plan-statuses")
	public List<String> getPlanStatuses(){
		
		return service.loadPlanStatuses();
	}
	
	@PostMapping("/search-plans")
	public List<SearchResponse> searchPlans(@RequestBody SearchForm form){
		
		return service.searchPlans(form);
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse httpResponse) throws Exception {
		
		httpResponse.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.xls";
		httpResponse.setHeader(headerKey, headerValue);	
		
		List<SearchResponse> response = service.searchPlans(null);
		service.exportExcelSheet(response, httpResponse);
	}
	
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse httpResponse) throws Exception {
		
		httpResponse.setContentType("application/pdf");
		String headerKey = "Content-Disposition"; //download
		String headerValue = "attachment; filename=Plans.pdf"; //format and filename
		httpResponse.setHeader(headerKey, headerValue);
		
		List<SearchResponse> response = service.searchPlans(null);
		service.exportPdf(response, httpResponse);
	}

}

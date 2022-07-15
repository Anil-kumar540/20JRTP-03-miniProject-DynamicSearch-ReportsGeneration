package com.poc.search.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.poc.search.bindings.SearchForm;
import com.poc.search.bindings.SearchResponse;
import com.poc.search.entity.EligibilityDetails;
import com.poc.search.repository.EligibilityDetailsRepo;
import com.poc.search.util.ReportsGenerateUtil;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private EligibilityDetailsRepo plansRepo;

	@Autowired
	private ReportsGenerateUtil reportUtil;

	@Override
	public List<String> loadPlanNames() {
		return plansRepo.getUniquePlanNames();
	}

	@Override
	public List<String> loadPlanStatuses() {
		return plansRepo.gatUniquePlanStatuses();
	}

	@Override
	public List<SearchResponse> searchPlans(SearchForm searchForm) {

		List<EligibilityDetails> eligRecords = null;
		if (isSearchFormEmpty(searchForm)) {
			eligRecords = plansRepo.findAll();
		} else {
			String planName = searchForm.getPlanName();
			String planStatus = searchForm.getPlanStatus();
			LocalDate startDate = searchForm.getStartDate();
			LocalDate endDate = searchForm.getEndDate();

			EligibilityDetails entity = new EligibilityDetails();

			if (planName != null && !planName.equals("")) {
				entity.setPlanName(planName);
			}
			if (planStatus != null && !planStatus.equals("")) {
				entity.setPlanStatus(planStatus);
			}
			if (startDate != null && endDate != null) {
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}

			Example<EligibilityDetails> of = Example.of(entity);

			eligRecords = plansRepo.findAll(of);
		}

		List<SearchResponse> result = new ArrayList<>();

		for (EligibilityDetails record : eligRecords) {

			SearchResponse response = new SearchResponse();
			BeanUtils.copyProperties(record, response);
			result.add(response);
		}

		return result;
	}

	public void exportExcelSheet(List<SearchResponse> response, HttpServletResponse httpResponse) throws Exception {
		reportUtil.exportExcelSheet(response, httpResponse);
	}

	public void exportPdf(List<SearchResponse> response, HttpServletResponse httpResponse) throws Exception {
		reportUtil.exportPdf(response, httpResponse);
	}

	private Boolean isSearchFormEmpty(SearchForm form) {

		if (form == null) {
			return true;
		}
		if (form.getPlanName() != null && !form.getPlanName().equals("")) {
			return false;
		}
		if (form.getPlanStatus() != null && !form.getPlanStatus().equals("")) {
			return false;
		}
		if (form.getStartDate() != null) {
			return false;
		}
		if (form.getEndDate() != null) {
			return false;
		}

		return true;
	}

}

package com.poc.search.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "ELIGIBILITY_DTLS")
public class EligibilityDetails {

	@Id
	@Column(name = "PLAN_ID")
	private Integer planId;
	@Column(name = "CASE_NUM")
	private Integer caseNum;
	@Column(name = "PLAN_NAME")
	private String planName;
	@Column(name = "PLAN_STATUS")
	private String planStatus;
	@Column(name = "HOLDER_NAME")
	private String  holderName;
	@Column(name = "HOLDER_SSN")
	private Integer holderSsn;
	@Column(name = "BENEFIT_AMT")
	private Double benefitAmount;
	@Column(name = "START_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@Column(name = "END_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	@Column(name = "DENIAL_REASN")
	private String denialReason;
}

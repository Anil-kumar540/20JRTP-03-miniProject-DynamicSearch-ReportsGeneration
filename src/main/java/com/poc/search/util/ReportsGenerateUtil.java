package com.poc.search.util;

import java.awt.Color;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.poc.search.bindings.SearchResponse;

@Component
public class ReportsGenerateUtil {

	public void exportExcelSheet(List<SearchResponse> response, HttpServletResponse httpResponse) throws Exception {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("plans");
		HSSFRow headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("S.No");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Holder SSN");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Start Date");
		headerRow.createCell(6).setCellValue("End Date");
		headerRow.createCell(7).setCellValue("Benefit Amount");
		headerRow.createCell(8).setCellValue("Denial Reason");

		for (int i = 1; i <= response.size(); i++) {

			HSSFRow dataRow = sheet.createRow(i);
			SearchResponse record = response.get(i - 1);

			dataRow.createCell(0).setCellValue(i);
			dataRow.createCell(1).setCellValue(record.getHolderName());
			if (record.getHolderSsn() != null)
				dataRow.createCell(2).setCellValue(String.valueOf(record.getHolderSsn()));
			dataRow.createCell(3).setCellValue(record.getPlanName());
			dataRow.createCell(4).setCellValue(record.getPlanStatus());
			if (record.getStartDate() != null)
				dataRow.createCell(5).setCellValue(String.valueOf(record.getStartDate()));
			if (record.getEndDate() != null)
				dataRow.createCell(6).setCellValue(String.valueOf(record.getEndDate()));
			if (record.getBenefitAmt() != null)
				dataRow.createCell(7).setCellValue(String.valueOf(record.getBenefitAmt()));
			if (record.getDenialReason() != null)
				dataRow.createCell(8).setCellValue(record.getDenialReason());
		}

		workbook.write(httpResponse.getOutputStream());
		workbook.close();

	}

	public void exportPdf(List<SearchResponse> response, HttpServletResponse httpResponse) throws Exception {

		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, httpResponse.getOutputStream());

		document.open();

		Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, Color.RED);
		Paragraph para = new Paragraph("Eligibility Details", font);
		document.add(para);

		PdfPTable table = new PdfPTable(9); // 9 columns

		table.addCell("S.No");
		table.addCell("Holder Name");
		table.addCell("Holder SSN");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");
		table.addCell("Denial Reason");

		int sno = 1;

		for (SearchResponse record : response) {

			table.addCell(String.valueOf(sno));
			table.addCell(record.getHolderName());
			table.addCell(String.valueOf(record.getHolderSsn()));
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());
			table.addCell(String.valueOf(record.getStartDate()));
			table.addCell(String.valueOf(record.getEndDate()));
			table.addCell(String.valueOf(record.getBenefitAmt()));
			table.addCell(record.getDenialReason());

			sno++;

		}

		document.add(table);
		document.close();
		writer.close();
	}

}

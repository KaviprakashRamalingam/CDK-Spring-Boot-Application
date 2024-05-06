package com.company.cdk.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.company.cdk.model.Job;
import com.company.cdk.model.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {
	@GetMapping("/report.htm")
	public void generateReport(HttpServletResponse response, HttpSession session, Model model) throws IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=report.pdf");
		Color blue = Color.BLUE;
		Color black = Color.BLACK;
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.beginText();
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
				contentStream.setLeading(14.5f);
				contentStream.newLineAtOffset(130, 750);
				contentStream.setNonStrokingColor(blue); // Blue color
				contentStream.showText("Career Development Kit - Report");
				contentStream.endText();
				contentStream.beginText();
				contentStream.setFont(PDType1Font.HELVETICA, 12);
				contentStream.setNonStrokingColor(black); // Black color
				contentStream.newLineAtOffset(90, 790);
				contentStream.endText();
				// Retrieve user details from session
				User loggedInUser = (User) session.getAttribute("loginuser");
				if (loggedInUser != null) {
					contentStream.beginText();
					contentStream.setNonStrokingColor(black); // Black color
					contentStream.newLineAtOffset(100, 720);
					contentStream.showText("Name: " + loggedInUser.getFullname());
					contentStream.newLine();
					contentStream.newLine();
					contentStream.showText("Address: " + loggedInUser.getAddress());
					contentStream.newLine();
					contentStream.newLine();
					contentStream.showText("Email: " + loggedInUser.getEmail());
					contentStream.newLine();
					contentStream.newLine();
					contentStream.endText();
				}
				if (loggedInUser.getUserType().equals("user")) {
				} else {
					float tableX = 100;
                    float tableY = 625;
                    float cellMargin = 12;
                    float rowHeight = 15;
                    float tableWidth = 490;
					@SuppressWarnings("unchecked")
					List<Job> jobPosts = (List<Job>) session.getAttribute("userJobPosts");
					if (jobPosts != null && !jobPosts.isEmpty()) {
						// Draw table border
	                    contentStream.setLineWidth(1);
	                    contentStream.addRect(tableX, tableY, tableWidth, rowHeight);
	                    contentStream.stroke();

	                    // Draw table headers
	                    contentStream.beginText();
	                    contentStream.newLineAtOffset(tableX + cellMargin, tableY + rowHeight - cellMargin);
	                    contentStream.showText("Job Title");
	                    contentStream.newLineAtOffset(150, 0);
	                    contentStream.showText("Company");
	                    contentStream.newLineAtOffset(200, 0);
	                    contentStream.showText("Place");
	                    contentStream.endText();

						// Display applications in table format
						float currentY = tableY - rowHeight;
	                    for (Job job : jobPosts) {
	                        currentY -= rowHeight;
	                        contentStream.beginText();
	                        contentStream.newLineAtOffset(tableX + cellMargin, currentY - cellMargin);
	                        contentStream.showText(job.getTitle());
	                        contentStream.newLineAtOffset(150, 0);
	                        contentStream.showText(job.getCompany());
	                        contentStream.newLineAtOffset(200, 0);
	                        contentStream.showText(job.getPlace());
	                        contentStream.endText();
	                    }
					}else {
						contentStream.beginText();
						contentStream.newLineAtOffset(100, 580);
						contentStream.showText("No Jobs Posted");
						contentStream.endText();
					}
				}
				
			}

			document.save(response.getOutputStream());
		}
	}
}
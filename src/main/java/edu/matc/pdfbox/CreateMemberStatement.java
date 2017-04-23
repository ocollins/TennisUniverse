package edu.matc.pdfbox;

/**
 * Created by student on 4/18/17.
 */

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import edu.matc.entity.Person;
import edu.matc.entity.PersonService;
import edu.matc.memberstatement.CalculateMonthlyStatement;
import edu.matc.persistence.PersonDao;
import edu.matc.persistence.PersonServiceDao;
import org.apache.log4j.Logger;
import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PageDrawer;

import javax.servlet.ServletContext;

public class CreateMemberStatement {

	CalculateMonthlyStatement calculateMonthlyStatement;
	PersonDao personDao;
	PersonServiceDao personServiceDao;
	private Logger logger = Logger.getLogger(this.getClass());


	/**
	 * Create body of the PDF document.
	 *
	 * @param memberId  the member id
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @throws IOException the io exception
	 */
	public void createPDF (int memberId, Date startDate, Date endDate, String fileName) throws IOException {
		logger.info("Getting into createPDF module");
		personDao = new PersonDao();
		personServiceDao = new PersonServiceDao();
		calculateMonthlyStatement = new CalculateMonthlyStatement();

		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		insertImage(contentStream, document);
		contentStream.setLeading(20);

		//Create a page title in the header
		createHeader(contentStream);

		//Add a line after the header
		drawRect(contentStream, 45, 680, 524, 1.5f);

		String billingNote = "With billing questions please call Tennis Universe at (608) 574 3397";
		printNote(contentStream, 670, billingNote);

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String printDate = dateTimeFormatter.format(LocalDate.now());
		String firstName = null;
		String lastName = null;
		String accountId = null;

		//Print member information at the top of page
		try {
			firstName = personDao.getPerson(memberId).getFirstName();
			lastName = personDao.getPerson(memberId).getLastName();
			accountId = String.valueOf(personDao.getPerson(memberId).getPersonId());
		} catch (Exception ex) {
			logger.info("Error getting member id or first and last name");
		}
		createMemInfoDetailLine(contentStream, 650, "Member Name", firstName + " " + lastName);
		createMemInfoDetailLine(contentStream, 630, "Customer Account:", accountId);

		String period = startDate + " to " + endDate;
		createMemInfoDetailLine(contentStream, 610, "Billing Period:", period);

		String totalDue = "$ " + String.valueOf(calculateMonthlyStatement.calculateTotalDue(memberId, startDate, endDate));
		createMemInfoDetailLine(contentStream, 590, "Total Amount Due:", totalDue);

		//Add a line after the member info
		drawRect(contentStream, 45, 580, 524, 1.5f);

		//Draw page border
		drawRect(contentStream, 20, 80, 570, 700);

		//Draw grey box for the Services and charges header
		drawBox(contentStream, 45, 530, 524, 27);

		//Print services and charges header
		drawHeaderRow(contentStream, 540, "Service", "Date", "Charge");

		//Create services detail lines
		List<PersonService> Services = calculateMonthlyStatement.listServices(memberId, startDate, endDate);
				float offset = 0f;
		float startY = 430f;
		for (PersonService service : Services) {
			drawServicesRows(contentStream, startY + offset, service.getService().getServiceDesc(),
					String.valueOf(service.getServiceDate()),
					String.valueOf("$ " + service.getService().getServiceCharge()));
			offset = offset + 20;
		}

		//Add a line before total due
		//drawRect(contentStream, 400, 395, 165, 1);

		//Draw grey box over the total due
		drawBox(contentStream, 400, 375, 165, 20);

		//Print total due
		drawServicesRows(contentStream, 380, "Total Due:", "", totalDue);

		//Print line before the pay online statement
		drawRect(contentStream, 45, 280, 524, 1.5f);

		billingNote = "Please pay your bill on line at https://tennisuniverse.com";
		printNote(contentStream, 270, billingNote);

		//Closing the content stream
		contentStream.close();

		fileName = fileName + firstName + lastName + printDate + ".pdf";
		//Saving the document
		try {
			document.save(fileName);
		} catch (IOException ex) {
			logger.info("Error writing Member statement file" + ex);
		}

		//Closing the document
		document.close();
	}

	/**
	 * Create header.
	 *
	 * @param contentStream the content stream
	 * @throws IOException the io exception
	 */
	public void createHeader(PDPageContentStream contentStream) throws IOException{
		//Draw grey box for the header
		drawBox(contentStream, 45, 700, 284, 54);
		//Create statement header
		contentStream.beginText();
		contentStream.setNonStrokingColor(Color.black);
		contentStream.newLineAtOffset(47, 720);
		//Setting the font to the Content stream
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
		contentStream.showText("Member Monthly Statement");
		//Ending the content stream
		contentStream.endText();

		//Draw a border around header
		drawRect(contentStream, 42, 697, 290, 60);


	}

	/**
	 * Insert image logo.
	 * @param contentStream the content stream
	 * @param document      the document
	 * @throws IOException the io exception
	 */
	public void insertImage(PDPageContentStream contentStream, PDDocument document) throws IOException{
		try {
			float scale = 0.2f;
			String imageUrl = "/home/student/EnterpriseRepos/TennisUniverse/src/main/webapp/images/header2.JPG";
			PDImageXObject pdImage = PDImageXObject.createFromFile(imageUrl, document);
			contentStream.drawImage(pdImage, 350, 700, pdImage.getWidth()*scale, pdImage.getHeight()*scale);

			//Add a grey border around the logo image
			contentStream.setStrokingColor(Color.GRAY);
			contentStream.addRect(350-3, 700-3, pdImage.getWidth()*scale+6, pdImage.getHeight()*scale+6);
			contentStream.closeAndStroke();
		} catch (IOException ex) {
			logger.info(ex);
		}
	}

	/**
	 * Print note.
	 *
	 * @param contentStream the content stream
	 * @param y             the y
	 * @param note          the note
	 * @throws IOException the io exception
	 */
	public void printNote(PDPageContentStream contentStream, float y, String note)
			throws IOException{
		contentStream.setNonStrokingColor(Color.black);
		//Begin the Content stream
		contentStream.beginText();

		//Setting the font to the Content stream
		contentStream.setFont(PDType1Font.HELVETICA, 8);

		//Setting the position for the line
		contentStream.newLineAtOffset(45, y);
		contentStream.showText(note);
		contentStream.newLine();
		//Ending the content stream
		contentStream.endText();

	}

	/**
	 * Create mem info detail line.
	 *
	 * @param contentStream the content stream
	 * @param y             the y
	 * @param label         the label
	 * @param info          the info
	 * @throws IOException the io exception
	 */
	public void createMemInfoDetailLine(PDPageContentStream contentStream, float y, String label, String info)
			throws IOException{
		//Begin the Content stream
		contentStream.beginText();
		contentStream.setNonStrokingColor(Color.black);
		contentStream.newLineAtOffset(45, y);
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		//Adding text in the form of string
		contentStream.showText(label);
		contentStream.newLineAtOffset(150, 0);
		contentStream.setFont(PDType1Font.HELVETICA, 12);

		//contentStream.newLineAtOffset(150, 0);
		contentStream.showText(info);

		contentStream.newLine();
		contentStream.endText();

	}

	/**
	 * Draw rect.
	 * @param contentStream the content stream
	 * @param x             the x
	 * @param y             the y
	 * @param w             the w
	 * @param h             the h
	 * @throws IOException the io exception
	 */
	public void drawRect (PDPageContentStream contentStream, float x, float y, float w, float h) throws IOException{
		contentStream.setStrokingColor(Color.GRAY);
		contentStream.addRect(x, y, w, h);
		contentStream.closeAndStroke();

	}

	/**
	 * Add box.
	 * @param contentStream the content stream
	 * @param x             the x
	 * @param y             the y
	 * @param w             the w
	 * @param h             the h
	 * @throws IOException the io exception
	 */
	public void drawBox(PDPageContentStream contentStream, float x, float y, float w, float h) throws IOException{
		//Setting the non stroking color
		contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
		//Drawing a rectangle
		contentStream.addRect(x, y, w, h);
		//Drawing a rectangle
		contentStream.fill();
		contentStream.closeAndStroke();
	}

	/**
	 * Draw services header.
	 * @param contentStream the content stream
	 * @throws IOException the io exception
	 */
	public void drawHeaderRow(PDPageContentStream contentStream, float y, String service, String date, String charge)
			throws IOException {
		contentStream.beginText();
		contentStream.setNonStrokingColor(Color.black);
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
		contentStream.newLineAtOffset(55, y);
		contentStream.showText(service);
		contentStream.newLineAtOffset(280, 0);
		contentStream.showText(date);
		contentStream.newLineAtOffset(150, 0);
		contentStream.showText(charge);
		contentStream.newLine();
		contentStream.endText();

	}

	/**
	 * Draw services detail line.
	 * @param contentStream the content stream
	 * @throws IOException the io exception
	 */
	public void drawServicesRows(PDPageContentStream contentStream, float y, String service, String date, String charge)
			throws IOException {
		contentStream.beginText();
		contentStream.setNonStrokingColor(Color.black);
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.newLineAtOffset(55, y);
		contentStream.showText(service);
		contentStream.newLineAtOffset(280, 0);
		contentStream.showText(date);
		contentStream.newLineAtOffset(150, 0);
		contentStream.showText(charge);
		contentStream.newLine();
		contentStream.endText();

	}



}

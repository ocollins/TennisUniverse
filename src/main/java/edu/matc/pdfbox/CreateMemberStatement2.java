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

public class CreateMemberStatement2 {

	CalculateMonthlyStatement calculateMonthlyStatement;
	PersonDao personDao;
	PersonServiceDao personServiceDao;
	private Logger logger = Logger.getLogger(this.getClass());


	public void createPDF (int memberId, Date startDate, Date endDate) throws IOException {
		personDao = new PersonDao();
		personServiceDao = new PersonServiceDao();

		float startYPosition = 670f;
		float startXPosition = 25;


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

		contentStream.setNonStrokingColor(Color.black);
		//Begin the Content stream
		contentStream.beginText();

		//Setting the font to the Content stream
		contentStream.setFont(PDType1Font.HELVETICA, 8);

		//Setting the position for the line
		contentStream.newLineAtOffset(45, startYPosition);
		contentStream.showText("With billing questions please call Tennis Universe at (608) 574 3397");
		contentStream.newLine();

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String printDate = dateTimeFormatter.format(LocalDate.now());

		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		//Adding text in the form of string
		contentStream.showText("Member Name: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		String firstName = personDao.getPerson(memberId).getFirstName();
		String lastName = personDao.getPerson(memberId).getLastName();
		contentStream.newLineAtOffset(150, 0);
		contentStream.showText(firstName + " "+ lastName );

		contentStream.newLine();
		contentStream.newLineAtOffset(0, 0);
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.showText("Customer Account: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.newLineAtOffset(50, 0);
		contentStream.showText(String.valueOf(personDao.getPerson(memberId).getPersonId()));

		contentStream.newLine();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.showText("Billing Period: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.newLineAtOffset(50, 0);
		contentStream.showText(startDate + " to " + endDate);

		contentStream.newLine();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.showText("Total Amount Due: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		calculateMonthlyStatement = new CalculateMonthlyStatement();
		contentStream.newLineAtOffset(150, 0);
		contentStream.showText(String.valueOf(calculateMonthlyStatement.calculateTotalDue(memberId, startDate, endDate)));
		contentStream.newLine();

		//Ending the content stream
		contentStream.endText();

		//Add a line after the member info
		drawRect(contentStream, 45, 580, 524, 1.5f);

		//Draw page border
		drawRect(contentStream, 20, 80, 570, 700);

		//Draw grey box for the header
		drawBox(contentStream, 45, 530, 500, 25);

		//Print services and charges header
		drawHeaderRow(contentStream, 540, "Service", "Date", "Charge");

		//Create services detail lines
		List<PersonService> Services = calculateMonthlyStatement.listServices(memberId, startDate, endDate);
				float offset = 0f;
		float startY = 430f;
		for (PersonService service : Services) {
			drawServicesRows(contentStream, startY + offset, service.getService().getServiceDesc(),
					String.valueOf(service.getServiceDate()),
					String.valueOf("$" + service.getService().getServiceCharge()));
			offset = offset + 20;
		}

		//Closing the content stream
		contentStream.close();
		//Saving the document
		document.save(new File("pdf/MemberStatement.pdf"));

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
		//Create statement header
		contentStream.beginText();
		contentStream.newLineAtOffset(45, 720);
		//Setting the font to the Content stream
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
		contentStream.showText("Member Monthly Statement");
		//Ending the content stream
		contentStream.endText();

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

	public void createMemInfoDetailLine(PDPageContentStream contentStream, float y, String label, String info)
			throws IOException{

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

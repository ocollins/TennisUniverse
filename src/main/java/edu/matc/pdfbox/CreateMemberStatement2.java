package edu.matc.pdfbox;

/**
 * Created by student on 4/18/17.
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import edu.matc.entity.Person;
import edu.matc.memberstatement.CalculateMonthlyStatement;
import edu.matc.persistence.PersonDao;
import edu.matc.persistence.PersonServiceDao;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class CreateMemberStatement2 {

	CalculateMonthlyStatement calculateMonthlyStatement;
	PersonDao personDao;
	PersonServiceDao personServiceDao;
	private Logger logger = Logger.getLogger(this.getClass());


	public void createPDF (int memberId, Date startDate, Date endDate) throws IOException {
		personDao = new PersonDao();
		personServiceDao = new PersonServiceDao();

		float startYPosition = 660f;
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		insertImage(contentStream, document);
		contentStream.setLeading(20);

		createHeader(contentStream);

		//Add a line after the header
		contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
		contentStream.addRect(45, 680, 524, 1.5f);
		contentStream.closeAndStroke();

		contentStream.setNonStrokingColor(Color.black);
		//Begin the Content stream
		contentStream.beginText();

		//Setting the font to the Content stream
		contentStream.setFont(PDType1Font.HELVETICA, 8);

		//Setting the position for the line
		contentStream.newLineAtOffset(45, startYPosition);
		contentStream.showText("Please call Tennis Universe with questions at (608) 574 3397");
		contentStream.newLine();


		//!!!!!!! input data
//		int memberId = 101;
//		Date startDate = Date.valueOf("2017-04-01");
//		Date endDate = Date.valueOf("2017-04-30");
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String printDate = dateTimeFormatter.format(LocalDate.now());

		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		//Adding text in the form of string
		contentStream.showText("Member Name: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		String firstName = personDao.getPerson(memberId).getFirstName();
		String lastName = personDao.getPerson(memberId).getLastName();
		contentStream.showText(firstName + " "+ lastName + "\052");

		contentStream.newLine();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.showText("Customer Account: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.showText(String.valueOf(personDao.getPerson(memberId).getPersonId()));

		contentStream.newLine();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.showText("Billing Period: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.showText(startDate + " to " + endDate);

		contentStream.newLine();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.showText("Total Amount Due: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		calculateMonthlyStatement = new CalculateMonthlyStatement();
		contentStream.showText(String.valueOf(calculateMonthlyStatement.calculateTotalDue(memberId, startDate, endDate)));

		//Ending the content stream
		contentStream.endText();

		contentStream.setStrokingColor(Color.GRAY);
		contentStream.addRect(20, 80, 570, 700);
		contentStream.closeAndStroke();

//		//Setting the non stroking color
//		contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
//		//Drawing a rectangle
//		contentStream.addRect(45, 680, 300, 40);
//		//Drawing a rectangle
//		contentStream.fill();
//		contentStream.closeAndStroke();

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
		contentStream.newLineAtOffset(45, 700);
		//Setting the font to the Content stream
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
		contentStream.showText("Member Monthly Statement");
		//Ending the content stream
		contentStream.endText();

	}

	/**
	 * Insert image.
	 *
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

}

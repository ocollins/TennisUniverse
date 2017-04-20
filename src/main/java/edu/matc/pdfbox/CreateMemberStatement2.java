package edu.matc.pdfbox;

/**
 * Created by student on 4/18/17.
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

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


	public void createPDF () throws IOException {
		personDao = new PersonDao();
		personServiceDao = new PersonServiceDao();

		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		insertImage(contentStream, document);

		contentStream.setNonStrokingColor(Color.LIGHT_GRAY);
		contentStream.addRect(45, 680, 524, 1.5f);
		contentStream.closeAndStroke();

		contentStream.setNonStrokingColor(Color.black);

		//Begin the Content stream
		contentStream.beginText();

		//Setting the font to the Content stream
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);

		//Setting the position for the line
		contentStream.newLineAtOffset(45, 660);

		//!!!!!!! input data
		int memberId = 101;
		Date begDate = "2017-04-01";
		String endDate = "2017/04/30";

		contentStream.setLeading(20);

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
		contentStream.showText(begDate + " to " + endDate);

		contentStream.newLine();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.showText("Total Amount Due: ");
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.showText();


		//Ending the content stream
		contentStream.endText();

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

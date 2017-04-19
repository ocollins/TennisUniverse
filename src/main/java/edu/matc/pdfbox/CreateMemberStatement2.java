package edu.matc.pdfbox;

/**
 * Created by student on 4/18/17.
 */

import edu.matc.memberstatement.CalculateMonthlyStatement;
import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class CreateMemberStatement2 {
    private final Logger logger = Logger.getLogger(this.getClass());
    CalculateMonthlyStatement calculateMonthlyStatement;

    public CreateMemberStatement2() throws IOException{
    }

    public void CreatePDF () {
		PDDocument doc = null;
		PDPage page = null;

		try{
			doc = new PDDocument();
			page = new PDPage();

			doc.addPage(page);
			PDFont font = PDType1Font.HELVETICA_BOLD;

			PDPageContentStream content = new PDPageContentStream(doc, page);
			content.beginText();
			content.setFont( font, 8 );

			content.moveTextPositionByAmount( 100, 700 );
			content.drawString(getMemberName(104));

			content.endText();
			content.close();
			doc.save("pdf/MemberStatement.pdf");
			doc.close();
		} catch (Exception e){
			System.out.println(e);
		}
	}

	public String getMemberName(int memberId) {
    	String firstName = calculateMonthlyStatement.printMemberInfo(memberId).getFirstName();
    	String lastName = calculateMonthlyStatement.printMemberInfo(memberId).getFirstName();
    	return firstName + lastName;

	}
}

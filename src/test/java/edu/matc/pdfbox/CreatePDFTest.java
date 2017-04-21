package edu.matc.pdfbox;

import edu.matc.entity.Person;
import edu.matc.entity.PersonService;
import edu.matc.entity.Service;
import edu.matc.memberstatement.CalculateMonthlyStatement;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class will test methods used to produce a monthly statement for a member
 *
 */
public class CreatePDFTest {
    private Logger logger = Logger.getLogger(this.getClass());

    int memberId = 101;
    Date startDate = Date.valueOf("2017-04-01");
    Date endDate = Date.valueOf("2017-04-30");

    @Test
    public void createPDFTest() throws Exception {
        CreateMemberStatement createMemberStatement = new CreateMemberStatement();
        createMemberStatement.createPDF(memberId, startDate, endDate);

    }


}
package edu.matc.pdfbox;

import edu.matc.entity.Person;
import edu.matc.memberstatement.CalculateMonthlyStatement;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

import static org.junit.Assert.*;

/**
 * Created by student on 4/18/17.
 */
public class CreateMemberStatementTest {
    private Logger logger = Logger.getLogger(this.getClass());

    Person member;
    CalculateMonthlyStatement calculateMonthlyStatement;

    @Before
    public void init() {
        calculateMonthlyStatement = new CalculateMonthlyStatement();
    }

    @Test
    public void createPDFTest() throws Exception {
        CreateMemberStatement2 createMemberStatement = new CreateMemberStatement2();
        createMemberStatement.createPDF();

    }

}
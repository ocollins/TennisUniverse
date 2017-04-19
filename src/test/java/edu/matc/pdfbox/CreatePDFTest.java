package edu.matc.pdfbox;

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
    private final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {
    }


    @Test
    public void calculateTotalDueTest () {

    }

}
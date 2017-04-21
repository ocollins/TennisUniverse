package edu.matc.pdfbox;

import edu.matc.entity.Person;
import edu.matc.memberstatement.CalculateMonthlyStatement;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by student on 4/18/17.
 */
public class CreateMemberStatementTest {
    private Logger logger = Logger.getLogger(this.getClass());

    Person member;
    CalculateMonthlyStatement calculateMonthlyStatement;
    int memberId = 101;
    Date startDate = Date.valueOf("2017-04-01");
    Date endDate = Date.valueOf("2017-04-30");

    @Before
    public void init() {

        calculateMonthlyStatement = new CalculateMonthlyStatement();
        memberId = 101;
        startDate = Date.valueOf("2017-04-01");
        endDate = Date.valueOf("2017-04-30");
    }


}
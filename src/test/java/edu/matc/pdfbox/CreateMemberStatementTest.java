package edu.matc.pdfbox;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by student on 4/18/17.
 */
public class CreateMemberStatementTest {
    @Test
    public void createPDFTest() throws Exception {
        CreateMemberStatement createMemberStatement = new CreateMemberStatement();
        createMemberStatement.CreatePDF();

    }

}
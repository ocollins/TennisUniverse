package edu.matc.pdfbox;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by student on 4/18/17.
 */
public class CreateMemberStatementTest {
    @Test
    public void createPDFTest() throws Exception {
        CreateMemberStatement2 createMemberStatement = new CreateMemberStatement2();
        createMemberStatement.CreatePDF();

    }

}
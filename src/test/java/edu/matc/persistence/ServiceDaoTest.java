package edu.matc.persistence;

import edu.matc.entity.Service;
import edu.matc.util.LocalDateAttributeConverter;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 02/04/17.
 * @author O Collins
 */
public class ServiceDaoTest {

    ServiceDao dao;
    LocalDateAttributeConverter dateConverter;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {

        dao = new ServiceDao();
    }

    @Test
    public void getAllServices() throws Exception {
        List<Service> services = dao.getAllServices();
        logger.info("Total services " + services.size());
        assertTrue(services.size() > 0);

        for (Service service : services) {
            logger.info("Service name " + service.getServiceDesc());
        }
    }

//    @Test
//    public void getService() throws Exception {
//        Service aService = dao.getService(101);
//
//        logger.info("Service with index 101 " + aService.getFirstName());
//        assertEquals("First name is not Joe ", "JOE", aService.getFirstName());
//    }
//
    @Test
    public void testGetServiceByDesc() throws Exception {
        Service service = dao.getServiceByDesc("PRIVATE LESSON");

        //Just testing stuff
        logger.info("Code for private lesson " + service.getServiceId());

        assertEquals("Service code for PRIVATE LESSON is not 01 ", "01", service.getServiceCode());
        assertEquals("Service ID for PRIVATE LESSON is not 20 ", 20, service.getServiceId());
    }

//
    @Test
    public void testAddService() throws Exception {
        Service newService = new Service("01", "JUNIOR LESSON", 50.00);
        int id = dao.addService(newService);
        logger.info("New service ID " + id);
        logger.info("Max service ID now " + dao.getMaxServiceId());

        //Check if a new service with new id exists
        assertNotNull("Service was not inserted ", dao.getService(id));
        assertEquals("Inserted service code is not 01 ", "01", dao.getService(id).getServiceCode());
        assertEquals("Inserted service description is not JUNIOR LESSON ", "JUNIOR LESSON", dao.getService(id).getServiceDesc());

        //Remove the that was just added due to SERVICE_DESC having unique constraint
        //dao.deleteService(id);
    }

    @Test
    public void deleteService() throws Exception {
        //Get ID for the last service in the SERVICE table
        int serviceIdToDelete = dao.getMaxServiceId();
        //Save the row data. It will be used to insert it back into the table
        Service saveService = dao.getService(serviceIdToDelete);

        logger.info("Deleting service ID " + serviceIdToDelete);
        dao.deleteService(serviceIdToDelete);

        assertNull("Service " + serviceIdToDelete + " is still there ", dao.getService(serviceIdToDelete));

        //Put deleted row back
        int id = dao.addService(saveService);

    }

    @Test
    public void testUpdateService() throws Exception {
        //Find the latest inserted service
        int serviceId = dao.getMaxServiceId();
        logger.info("Update service ID " + serviceId);

        //Save the row data. It will be used to insert it back into the table
        Service saveService = dao.getService(serviceId);

        //Create service information to update in the DB
        Service serviceToUpdate =  new Service(serviceId, "01", "JUNIOR LESSON", 50.00);

        //Update person
        dao.updateService(serviceToUpdate);

        //Test the update
        assertEquals("Updated service code is not 01 ", "01", dao.getService(serviceId).getServiceCode());
        assertEquals("Updated service description is not JUNIOR LESSON ", "JUNIOR LESSON", dao.getService(serviceId).getServiceDesc());

        //Put updated row back the way it was before
        int id = dao.addService(saveService);


    }

}
package edu.matc.memberstatement;

import edu.matc.entity.Person;
import edu.matc.entity.PersonService;
import edu.matc.persistence.PersonDao;
import edu.matc.persistence.PersonServiceDao;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;

/**
 * Class will have methods, used to produce monthly statement for a member
 *
 */
public class CalculateMonthlyStatement {
    private Logger logger = Logger.getLogger(this.getClass());
    PersonServiceDao personServiceDao;
    PersonDao personDao;
    Person member;

    /**
     * List all services for a member for the specified period.
     *
     * @param id        the id
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    public List<PersonService> listServices(int id, Date startDate, Date endDate) {
        personServiceDao = new PersonServiceDao();
        //Get a list of services per member per period from personServiceDAO
        List<PersonService> personServicesList =
                personServiceDao.getPersonService(id, startDate, endDate);

        for(PersonService personService : personServicesList) {
            logger.info(personService.getService().getServiceDesc() + " " + personService.getServiceDate());
        }
        return personServicesList;
    }

    /**
     * Calculate total due double.
     *
     * @param id        the id
     * @param startDate the start date
     * @param endDate   the end date
     * @return the double
     */
    public double calculateTotalDue(int id, Date startDate, Date endDate) {
        List<PersonService> personServicesList = listServices(id, startDate, endDate);
        double totalDue = 0.0;
        for(PersonService personService : personServicesList) {
            totalDue = totalDue + personService.getService().getServiceCharge();
            logger.info("Accumulating total charge " + totalDue);
        }

        logger.info("Total due " + totalDue);
        return totalDue;
    }


}

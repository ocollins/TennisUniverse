package edu.matc.entity;

import javax.persistence.*;
import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

/**
 * Class to describe services used by members
 * @author O Collins
 */
@Entity
@Table(name = "PERSON_SERVICE", schema = "TENNIS_UNIVERSE")
public class PersonService {
    @Id
    @Column(name = "PERSON_SERVICE_ID")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int personServiceId;

    @Column(name = "SERVICE_DATE")
    private Date serviceDate;

    @Column(name = "NOTES")
    private String notes;

    @ManyToOne
    @JoinColumn(name="PERSON_ID")
    private Person persons;

    public PersonService () {

    }


    public int getPersonServiceId() {
        return personServiceId;
    }

    public void setPersonServiceId(int personServiceId) {
        this.personServiceId = personServiceId;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}

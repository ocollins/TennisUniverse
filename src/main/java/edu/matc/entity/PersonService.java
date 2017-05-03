package edu.matc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

/**
 * Class to describe services used by members
 * @author O Collins
 */
@Entity
@Table(name = "PERSON_SERVICE", schema = "TENNIS_UNIVERSE")
public class PersonService implements Serializable{
    @Id
    @Column(name = "PERSON_SERVICE_ID")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int personServiceId;

    @Column(name = "PERSON_ID")
    private int personId;

    @Column(name = "SERVICE_DATE")
    private Date serviceDate;

    @Column(name = "NOTES")
    private String notes;

    @Column
    private Service service;

    public PersonService () {
    }


    public int getPersonServiceId() {
        return personServiceId;
    }

    public void setPersonServiceId(int personServiceId) {
        this.personServiceId = personServiceId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SERVICE_ID", nullable = false, updatable = false)
    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }


}
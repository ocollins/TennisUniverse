package edu.matc.entity;

import javax.persistence.*;
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
public class PersonService {
    @Id
    @Column(name = "PERSON_SERVICE_ID")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int personServiceId;

    @Column(name = "PERSON_ID")
    private int personId;

//    @Column(name = "SERVICE_ID")
//    private int serviceId;

    @Column(name = "SERVICE_DATE")
    private LocalDate serviceDate;

    @Column(name = "NOTES")
    private String notes;

    @Column
    private Service service;

    public PersonService () {
    }

    public PersonService (int personId, int personServiceId, LocalDate serviceDate, String notes) {
        this.personId = personId;
        this.personServiceId = personServiceId;
        this.serviceDate = serviceDate;
        this.notes = notes;

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

//    public int getServiceId() {
//        return serviceId;
//    }
//
//    public void setServiceId(int serviceId) {
//        this.serviceId = serviceId;
//    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
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

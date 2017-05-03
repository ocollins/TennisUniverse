package edu.matc.entity;

import edu.matc.util.LocalDateAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


import static java.time.LocalDate.now;

/**
 * A class to represent a person.
 *
 * @author OCollins
 */
@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "PERSON_ID")
    private int personId;

    @Column(name = "SSN_NR")
    private int ssnNr;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DT")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate birthDt;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "EMAIL_ADDR")
    private String emailAddr;

    @Column(name = "ADDRESS_LINE1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "LAST_UPDATED")
    private Timestamp lastUpdated;

    private Set<Service> services = new HashSet<Service>(0);

    /**
     * Instantiates a new Person.
     */
    public Person() {
    }

    /**
     * Instantiates a new Person.
     *
     * @param ssnNr        the ssn nr
     * @param firstName    the first name
     * @param lastName     the last name
     * @param birthDt      the birth dt
     * @param roleName     the user role name
     * @param emailAddr    the email addr
     * @param addressLine1 the address line 1
     * @param addressLine2 the address line 2
     * @param city         the city
     * @param state        the state
     * @param zip          the zip
     * @param phone        the member phone number
     //* @param lastUpdated  the last updated
     */
    public Person(int ssnNr, String firstName, String lastName, LocalDate birthDt, String roleName,
                  String emailAddr, String addressLine1, String addressLine2, String city,
                  String state, String zip, String phone) {
        this.ssnNr = ssnNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDt = birthDt;
        this.roleName = roleName;
        this.emailAddr = emailAddr;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        setLastUpdated();
        this.lastUpdated = getLastUpdated();
    }

    /**
     * Instantiates a new Person.
     *
     * @param personId     the person id
     * @param ssnNr        the ssn nr
     * @param firstName    the first name
     * @param lastName     the last name
     * @param birthDt      the birth dt
     * @param roleName     the user role name
     * @param emailAddr    the email addr
     * @param addressLine1 the address line 1
     * @param addressLine2 the address line 2
     * @param city         the city
     * @param state        the state
     * @param zip          the zip
     * @param phone        the usta rating
     */
    public Person(int personId, int ssnNr, String firstName, String lastName, LocalDate birthDt, String roleName,
                  String emailAddr, String addressLine1, String addressLine2, String city,
                  String state, String zip, String phone) {
        this.personId = personId;
        this.ssnNr = ssnNr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDt = birthDt;
        this.roleName = roleName;
        this.emailAddr = emailAddr;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        setLastUpdated();
        this.lastUpdated = getLastUpdated();
    }

    /**
     * Gets person id.
     *
     * @return the person id
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Sets person id.
     *
     * @param personId the person id
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * Gets ssn nr.
     *
     * @return the ssn nr
     */
    public int getSsnNr() {
        return ssnNr;
    }

    /**
     * Sets ssn nr.
     *
     * @param ssnNr the ssn nr
     */
    public void setSsnNr(int ssnNr) {
        this.ssnNr = ssnNr;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets birth dt.
     *
     * @return the birth dt
     */
    public LocalDate getBirthDt() {
        return birthDt;
    }

    /**
     * Sets birth dt.
     *
     * @param birthDt the birth dt
     */
    public void setBirthDt(LocalDate birthDt) {
        this.birthDt = birthDt;
    }

    /**
     * Gets person type.
     *
     * @return the person type
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets person type.
     *
     * @param roleName the user role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets email addr.
     *
     * @return the email addr
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * Sets email addr.
     *
     * @param emailAddr the email addr
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    /**
     * Gets address line 1.
     *
     * @return the address line 1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets address line 1.
     *
     * @param addressLine1 the address line 1
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Gets address line 2.
     *
     * @return the address line 2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets address line 2.
     *
     * @param addressLine2 the address line 2
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets zip.
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets zip.
     *
     * @param zip the zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets usta rating.
     *
     * @return the usta rating
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets usta rating.
     *
     * @param phone the usta rating
     */
    public void setphone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets last updated.
     *
     * @return the last updated
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets last updated.
     *
     */
    public void setLastUpdated() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        this.lastUpdated = currentTimestamp;
    }

    @Access(AccessType.PROPERTY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Service.class)
    @JoinTable(name = "PERSON_SERVICE",
            joinColumns = {@JoinColumn(name = "PERSON_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "SERVICE_ID", nullable = false, updatable = false) })
    public Set<Service> getServices() {
        return this.services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }


    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", ssnNr=" + ssnNr +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDt=" + birthDt +
                ", roleName='" + roleName + '\'' +
                ", emailAddr='" + emailAddr + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
package edu.matc.entity;

/**
 * A class to a list of USERs.
 * @author O Collins
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent a person.
 *
 * @author OCollins
 */
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "PERSON_ID", unique = true)
    private int personId;

    @Column(name = "USER_NAME")
    private String userName ;

    @Column(name = "USER_PASS")
    private String userPass;


    /**
     * Instantiates a new USER.
     */
    public User() {
    }

    /**
     * Instantiates a new USER.
     *
     * @param userName  the USER name
     * @param userPass   the USER password
     */
    public User(int personId, String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    /**
     * Gets USER id.
     *
     * @return the USER id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     * @param userId the USER id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets person id.
     * @return the person id
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Sets person id.
     * @param personId the person id
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * Gets USER name.
     * @return the USER name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets USER name.
     * @param userName the USER name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets USER password.
     * @return the USER password
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     * Sets USER desc.
     * @param userPass the USER desc
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }


    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + "}";
    }
}
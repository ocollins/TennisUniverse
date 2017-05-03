package edu.matc.entity;

import javax.persistence.*;
import java.sql.Date;
import edu.matc.util.LocalDateAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class to describe Person account
 *
 * @author O Collins
 */
@Entity
@Table(name = "PERSON_ACCOUNT", schema = "TENNIS_UNIVERSE")
public class PersonAccount {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int accountId;

    @Column(name = "EFF_DATE")
    private Date effDate;

    @Column(name = "EXP_DATE")
    private Date expDate;

    @Column(name = "NOTES")
    private String notes;

    public PersonAccount() {
    }

    /**
     * Instantiates a new Person account entity.
     *
     * @param accountId the account id
     * @param effDate   the eff date
     * @param expDate   the exp date
     * @param notes     the notes
     */
    public PersonAccount(int accountId, Date effDate, Date expDate, String notes) {
        this.accountId = accountId;
        this.effDate = effDate;
        this.expDate = expDate;
        this.notes = notes;
    }

    /**
     * Gets account id.
     * @return the account id
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Sets account id.
     * @param accountId the account id
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets eff date.
     * @return the eff date
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * Sets eff date.
     * @param effDate the eff date
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * Gets exp date.
     * @return the exp date
     */
    public Date getExpDate() {
        return expDate;
    }

    /**
     * Sets exp date.
     * @param expDate the exp date
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }


    /**
     * Gets notes.
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }


}

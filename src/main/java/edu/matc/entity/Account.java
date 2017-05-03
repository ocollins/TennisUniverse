package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent an Account.
 *
 * @author OCollins
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ACCOUNT_TYPE")
    private int accountId;

    @Column(name = "ACCOUNT_DESC")
    private String accountDesc ;

    @Column(name = "MONTHLY_CHARGE")
    private double monthlyCharge;

    /**
     * Instantiates a new Account.
     */
    public Account() {
    }

    /**
     * Instantiates a new Account.
     *
     * @param accountId     the account id
     * @param accountDesc   the account desc
     * @param monthlyCharge the monthly charge
     */
    public Account(int accountId, String accountDesc, double monthlyCharge) {
        this.accountId = accountId;
        this.accountDesc = accountDesc;
        this.monthlyCharge = monthlyCharge;
    }

    /**
     * Instantiates a new Account.
     *
     * @param accountDesc   the account desc
     * @param monthlyCharge the monthly charge
     */
    public Account(String accountDesc, double monthlyCharge) {
        this.accountDesc = accountDesc;
        this.monthlyCharge = monthlyCharge;
    }

    /**
     * Gets account id.
     *
     * @return the account id
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Sets account id.
     *
     * @param accountId the account id
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets account desc.
     *
     * @return the account desc
     */
    public String getAccountDesc() {
        return accountDesc;
    }

    /**
     * Sets account desc.
     *
     * @param accountDesc the account desc
     */
    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    /**
     * Gets monthly charge.
     *
     * @return the monthly charge
     */
    public double getMonthlyCharge() {
        return monthlyCharge;
    }

    /**
     * Sets monthly charge.
     *
     * @param monthlyCharge the monthly charge
     */
    public void setMonthlyCharge(double monthlyCharge) {
        this.monthlyCharge = monthlyCharge;
    }
}
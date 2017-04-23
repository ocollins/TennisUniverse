package edu.matc.entity;

/**
 * A class to a list of USER_ROLE Roles.
 * @author O Collins
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent USER_ROLE roles.
 * @author OCollins
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "USER_ROLE_ID")
    private int userRoleId;

    @Column(name = "USER_NAME")
    private String userName ;

    @Column(name = "ROLE_NAME")
    private String roleName;


    /**
     * Instantiates a new USER_ROLE.
     */
    public UserRole() {
    }

    /**
     * Instantiates a new USER_ROLE.
     *
     * @param userName  the USER_ROLE name
     * @param roleName   the USER_ROLE password
     */
    public UserRole(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    /**
     * Gets USER_ROLE id.
     * @return the USER_ROLE id
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * Sets USER_ROLE id.
     *
     * @param userRoleId the USER_ROLE id
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user role name.
     *
     * @return the user role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets user role name.
     *
     * @param roleName the user role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public String toString() {
        return "User Role {" +
                "userRoleId=" + userRoleId +
                ", userName='" + userName + '\'' +
                ", roleName='" + roleName + "}";
    }
}
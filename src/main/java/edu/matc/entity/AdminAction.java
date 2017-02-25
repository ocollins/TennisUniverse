package edu.matc.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class to represent Admin Actions
 *
 * @author O Collins
 */
@Entity
@Table(name = "ADMIN_ACTION", schema = "TENNIS_UNIVERSE")
public class AdminAction {
    @Id
    @Column(name = "ADMIN_ACTION_ID")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int adminActionId;

    @Column(name = "ACTION_DESC")
    private String actionDesc;

    /**
     * Instantiates a new Admin action.
     */
    public AdminAction() {
    }

    /**
     * Instantiates a new Admin action.
     *
     * @param adminActionId the admin action id
     * @param actionDesc    the action desc
     */
    public AdminAction (int adminActionId, String actionDesc) {
        this.adminActionId = adminActionId;
        this.actionDesc = actionDesc;
    }

    /**
     * Gets admin action id.
     *
     * @return the admin action id
     */
    public int getAdminActionId() {
        return adminActionId;
    }

    /**
     * Sets admin action id.
     *
     * @param adminActionId the admin action id
     */
    public void setAdminActionId(int adminActionId) {
        this.adminActionId = adminActionId;
    }

    /**
     * Gets action desc.
     *
     * @return the action desc
     */
    public String getActionDesc() {
        return actionDesc;
    }

    /**
     * Sets action desc.
     *
     * @param actionDesc the action desc
     */
    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }


}

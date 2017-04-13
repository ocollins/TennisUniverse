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

    @Column(name = "ACTION_JSP_NAME")
    private String actionJSPName;

    @Column(name = "SEARCH_MEMBER_SW")
    private String searchMemberSw;

    /**
     * Instantiates a new Admin action.
     */
    public AdminAction() {
    }

    /**
     * Instantiates a new Admin action.
     * @param actionDesc        the action desc
     * @param actionServletName the action servlet name
     */
    public AdminAction(String actionDesc, String actionServletName) {
        this.actionDesc = actionDesc;
        this.actionJSPName = actionServletName;
    }

    /**
     * Gets admin action id.
     * @return the admin action id
     */
    public int getAdminActionId() {
        return adminActionId;
    }

    /**
     * Sets admin action id.
     * @param adminActionId the admin action id
     */
    public void setAdminActionId(int adminActionId) {
        this.adminActionId = adminActionId;
    }

    /**
     * Gets action desc.
     * @return the action desc
     */
    public String getActionDesc() {
        return actionDesc;
    }

    /**
     * Sets action desc.
     * @param actionDesc the action desc
     */
    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    /**
     * Gets action servlet name.
     * @return the action servlet name
     */
    public String getActionJSPName() {
        return actionJSPName;
    }

    /**
     * Sets action jsp name.
     * @param actionJSPName the action jsp name
     */
    public void setActionJSPName(String actionJSPName) {
        this.actionJSPName = actionJSPName;
    }

    /**
     * Gets search member sw.
     * @return the search member sw
     */
    public String getSearchMemberSw() {
        return searchMemberSw;
    }

    /**
     * Sets search member sw.
     * @param searchMemberSw the search member sw
     */
    public void setSearchMemberSw(String searchMemberSw) {
        this.searchMemberSw = searchMemberSw;
    }
}

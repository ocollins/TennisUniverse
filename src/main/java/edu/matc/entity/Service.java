package edu.matc.entity;

/**
 * A class to represent a services provided to the club members.
 * @author O Collins
 */

import edu.matc.util.LocalDateAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * A class to represent a person.
 *
 * @author OCollins
 */
@Entity
@Table(name = "SERVICE")
public class Service {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "SERVICE_ID")
    private int serviceId;

    @Column(name = "SERVICE_CODE")
    private String serviceCode ;

    @Column(name = "SERVICE_DESC")
    private String serviceDesc;

    @Column(name = "SERVICE_CHARGE")
    private double serviceCharge;

    /**
     * Instantiates a new Person.
     */
    public Service() {
    }

    /**
     * Instantiates a new Service.
     *
     * @param serviceId     the service id
     * @param serviceCode   the service code
     * @param serviceDesc   the service desc
     * @param serviceCharge the service charge
     */
    public Service(int serviceId, String serviceCode, String serviceDesc, double serviceCharge) {
        this.serviceId = serviceId;
        this.serviceCode = serviceCode;
        this.serviceDesc = serviceDesc;
        this.serviceCharge = serviceCharge;

    }

    /**
     * Gets service id.
     *
     * @return the service id
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Sets service id.
     *
     * @param serviceId the service id
     */
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * Gets service code.
     *
     * @return the service code
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets service code.
     *
     * @param serviceCode the service code
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Gets service desc.
     *
     * @return the service desc
     */
    public String getServiceDesc() {
        return serviceDesc;
    }

    /**
     * Sets service desc.
     *
     * @param serviceDesc the service desc
     */
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    /**
     * Gets service charge.
     *
     * @return the service charge
     */
    public double getServiceCharge() {
        return serviceCharge;
    }

    /**
     * Sets service charge.
     *
     * @param serviceCharge the service charge
     */
    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", serviceCode='" + serviceCode + '\'' +
                ", serviceDesc='" + serviceDesc + '\'' +
                ", serviceCharge=" + serviceCharge +
                '}';
    }
}
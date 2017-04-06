/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Selen
 */
public class Department {
    int depId;
    int firmId;
    String depName;
    BigDecimal managerId;
    String depMail;
    String phoneNumber;

    public Department(int depId, int firmId, String depName, BigDecimal managerId, String depMail, String phoneNumber) {
        this.depId = depId;
        this.firmId = firmId;
        this.depName = depName;
        this.managerId = managerId;
        this.depMail = depMail;
        this.phoneNumber = phoneNumber;
    }

    public Department() {
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public BigDecimal getManagerId() {
        return managerId;
    }

    public void setManagerId(BigDecimal managerId) {
        this.managerId = managerId;
    }

    public String getDepMail() {
        return depMail;
    }

    public void setDepMail(String depMail) {
        this.depMail = depMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    
}

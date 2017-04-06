
package Model;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author Selen
 */
public class Employee {
    BigDecimal ssn;
    String empName;
    String empAddr;
    String phoneNumber;
    String email;
    int depId;
    Date startTime;
    int officeId;

    public Employee(BigDecimal ssn, String empName, String empAddr, String phoneNumber, String email, int depId, Date startTime, int officeId) {
        this.ssn = ssn;
        this.empName = empName;
        this.empAddr = empAddr;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.depId = depId;
        this.startTime = startTime;
        this.officeId = officeId;
    }

    public Employee() {
    }

    public BigDecimal getSsn() {
        return ssn;
    }

    public void setSsn(BigDecimal ssn) {
        this.ssn = ssn;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddr() {
        return empAddr;
    }

    public void setEmpAddr(String empAddr) {
        this.empAddr = empAddr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Selen
 */
public class Project {
    int projectId;
    String proName;
    int impId;
    Date startDate;
    Date dueDate;
    BigDecimal managerId;
    int deptID;
    String exp;

    public Project(int projectId, String proName, int impId, Date startDate, Date dueDate, BigDecimal managerId, int deptID, String exp) {
        this.projectId = projectId;
        this.proName = proName;
        this.impId = impId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.managerId = managerId;
        this.deptID = deptID;
        this.exp = exp;
    }

    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getImpId() {
        return impId;
    }

    public void setImpId(int impId) {
        this.impId = impId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getManagerId() {
        return managerId;
    }

    public void setManagerId(BigDecimal managerId) {
        this.managerId = managerId;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
    
    
    
}

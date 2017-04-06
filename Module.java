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
public class Module {
    int modId;
    String ModName;
    Date startDate;
    Date dueDate;
    int statusId;
    String exp;
    BigDecimal managerId;
    int proId;

    public Module(int modId, String ModName, Date startDate, Date dueDate, int statusId, String exp, BigDecimal managerId, int proId) {
        this.modId = modId;
        this.ModName = ModName;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.statusId = statusId;
        this.exp = exp;
        this.managerId = managerId;
        this.proId = proId;
    }

    public Module() {
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
    }

    public String getModName() {
        return ModName;
    }

    public void setModName(String ModName) {
        this.ModName = ModName;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public BigDecimal getManagerId() {
        return managerId;
    }

    public void setManagerId(BigDecimal managerId) {
        this.managerId = managerId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }
    
    
    
}

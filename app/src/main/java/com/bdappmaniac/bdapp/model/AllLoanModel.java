package com.bdappmaniac.bdapp.model;

public class AllLoanModel {
    String empName;
    String takingDate;
    String amount;
    public AllLoanModel(String empName, String takingDate, String amount)
    {
        this.empName =empName;
        this.takingDate =takingDate;
        this.amount =amount;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getTakingDate() {
        return takingDate;
    }

    public void setTakingDate(String takingDate) {
        this.takingDate = takingDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

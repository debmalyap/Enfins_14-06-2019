package com.qbent.enfinsapp.model;

import java.util.Date;

public class LoanApplication
{
    private String applicationNo;
    private String applicationDate;
    private String branch;
    private String borrowerName;
    private String coBorrowerName;
    private String loanProduct;
    private String loanPurpose;
    private String status;

    public LoanApplication(String applicationNo, String applicationDate, String branch, String borrowerName, String coBorrowerName, String loanProduct, String loanPurpose, String status) {
        this.applicationNo = applicationNo;
        this.applicationDate = applicationDate;
        this.branch = branch;
        this.borrowerName = borrowerName;
        this.coBorrowerName = coBorrowerName;
        this.loanProduct = loanProduct;
        this.loanPurpose = loanPurpose;
        this.status = status;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getBranch() {
        return branch;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getCoBorrowerName() {
        return coBorrowerName;
    }

    public String getLoanProduct() {
        return loanProduct;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public String getStatus() {
        return status;
    }
}

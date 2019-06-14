package com.qbent.enfinsapp.model;


public class Member {
    private String id;
    private String code;
    private String fullName;
    private Long aadharNo;
    private String dateOfDeath;
    private String guardianName;
    private String collectionPointName;
    private String visitStatus;
    //private String actionStatus;

    public Member(String id,String code, String fullName, Long aadharNo, String dateOfDeath, String guardianName, String collectionPointName, String visitStatus) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.aadharNo = aadharNo;
        this.dateOfDeath = dateOfDeath;
        this.guardianName = guardianName;
        this.collectionPointName = collectionPointName;
        this.visitStatus = visitStatus;
        //this.actionStatus = actionStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getCollectionPointName() {
        return collectionPointName;
    }

    public void setCollectionPointName(String collectionPointName) {
        this.collectionPointName = collectionPointName;
    }

    public String getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(String visitStatus) {
        this.visitStatus = visitStatus;
    }


}

package com.klenovszky.challenge.entity;

public class OutPayHeaderEntity {
    private long id;
    private String clntnum;
    private String chdrnum;
    private String letterType;
    private String printDate;
    private String dataID;
    private String clntName;
    private String clntAddress;
    private Double benPercent;
    private String role1;
    private String role2;
    private String cownNum;
    private String cownName;
    private String dummy;

    public OutPayHeaderEntity() {
    }

    public OutPayHeaderEntity(String clntnum, String chdrnum, String letterType, String printDate, String dataID, String clntName, String clntAddress, Double benPercent, String role1, String role2, String cownNum, String cownName) {
        this.clntnum = clntnum;
        this.chdrnum = chdrnum;
        this.letterType = letterType;
        this.printDate = printDate;
        this.dataID = dataID;
        this.clntName = clntName;
        this.clntAddress = clntAddress;
        this.benPercent = benPercent;
        this.role1 = role1;
        this.role2 = role2;
        this.cownNum = cownNum;
        this.cownName = cownName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClntnum() {
        return clntnum;
    }

    public void setClntnum(String clntnum) {
        this.clntnum = clntnum;
    }

    public String getChdrnum() {
        return chdrnum;
    }

    public void setChdrnum(String chdrnum) {
        this.chdrnum = chdrnum;
    }

    public String getLetterType() {
        return letterType;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getClntName() {
        return clntName;
    }

    public void setClntName(String clntName) {
        this.clntName = clntName;
    }

    public String getClntAddress() {
        return clntAddress;
    }

    public void setClntAddress(String clntAddress) {
        this.clntAddress = clntAddress;
    }

    public Double getBenPercent() {
        return benPercent;
    }

    public void setBenPercent(Double benPercent) {
        this.benPercent = benPercent;
    }

    public String getRole1() {
        return role1;
    }

    public void setRole1(String role1) {
        this.role1 = role1;
    }

    public String getRole2() {
        return role2;
    }

    public void setRole2(String role2) {
        this.role2 = role2;
    }

    public String getCownNum() {
        return cownNum;
    }

    public void setCownNum(String cownNum) {
        this.cownNum = cownNum;
    }

    public String getCownName() {
        return cownName;
    }

    public void setCownName(String cownName) {
        this.cownName = cownName;
    }

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }
}

package com.klenovszky.challenge.entity;

public class PolicyEntity {
    private long id;
    private String chdrNum;
    private String cownNum;
    private String ownerName;
    private String lifcNum;
    private String lifcName;
    private String aracde;
    private String agntNum;
    private String mailAddress;
    private String dummy;

    public PolicyEntity() {
    }

    public PolicyEntity(String chdrNum, String cownNum, String ownerName, String lifcNum, String lifcName, String aracde, String agntNum, String mailAddress) {
        this.chdrNum = chdrNum;
        this.cownNum = cownNum;
        this.ownerName = ownerName;
        this.lifcNum = lifcNum;
        this.lifcName = lifcName;
        this.aracde = aracde;
        this.agntNum = agntNum;
        this.mailAddress = mailAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChdrNum() {
        return chdrNum;
    }

    public void setChdrNum(String chdrNum) {
        this.chdrNum = chdrNum;
    }

    public String getCownNum() {
        return cownNum;
    }

    public void setCownNum(String cownNum) {
        this.cownNum = cownNum;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLifcNum() {
        return lifcNum;
    }

    public void setLifcNum(String lifcNum) {
        this.lifcNum = lifcNum;
    }

    public String getLifcName() {
        return lifcName;
    }

    public void setLifcName(String lifcName) {
        this.lifcName = lifcName;
    }

    public String getAracde() {
        return aracde;
    }

    public void setAracde(String aracde) {
        this.aracde = aracde;
    }

    public String getAgntNum() {
        return agntNum;
    }

    public void setAgntNum(String agntNum) {
        this.agntNum = agntNum;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }
}

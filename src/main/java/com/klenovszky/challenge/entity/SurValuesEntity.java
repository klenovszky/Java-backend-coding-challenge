package com.klenovszky.challenge.entity;

public class SurValuesEntity {
    private long id;
    private String chdrNum;
    private Double surValue;
    private String company;
    private String currency;
    private String validDate;

    public SurValuesEntity() {
    }

    public SurValuesEntity(String company, String chdrNum, Double surValue, String validDate) {
        this.chdrNum = chdrNum;
        this.surValue = surValue;
        this.company = company;
        this.validDate = validDate;
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

    public Double getSurValue() {
        return surValue;
    }

    public void setSurValue(Double surValue) {
        this.surValue = surValue;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
}

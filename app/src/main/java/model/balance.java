package model;

import java.io.Reader;

public class balance {
    private String name,dorc;
    private Double balance;
private Double crbalance;
private Double drbalance;
private String remarks;private String date;

    public Double getCrbalance() {
        return crbalance;
    }

    public balance(String name,  Double crbalance, Double drbalance, String remarks, String date) {
        this.name = name;

        this.crbalance = crbalance;
        this.drbalance = drbalance;
        this.remarks = remarks;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCrbalance(Double crbalance) {
        this.crbalance = crbalance;
    }

    public Double getDrbalance() {
        return drbalance;
    }

    public void setDrbalance(Double drbalance) {
        this.drbalance = drbalance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public balance(String name, String dorc, Double balance) {
        this.name = name;
        this.dorc = dorc;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDorc() {
        return dorc;
    }

    public void setDorc(String dorc) {
        this.dorc = dorc;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

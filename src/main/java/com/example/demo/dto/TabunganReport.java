package com.example.demo.dto;

import java.util.Date;


public interface TabunganReport {

    public String getDescription();
    public Date getDate();
    public Long getCredit();
    public Long getDebit();
    public Long getBalance();

}

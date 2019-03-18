package com.example.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


@Value
public class TabunganReportDto implements Serializable {

    private static final long serialVersionUID = 9052922268161762222L;

    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";
    public static final String CREDIT = "credit";
    public static final String DEBIT = "debit";
    public static final String BALANCE = "balance";


    private String description;
    private Date date;
    private Long credit;
    private Long debit;
    private Long balance;

    public TabunganReportDto(Map<String, Object> values) {
        this.description = values.get(DESCRIPTION) != null ? (String) values.get(DESCRIPTION): null;
        this.date = values.get(DATE) != null ? (Date) values.get(DATE): null;
        this.credit = values.get(CREDIT) != null ? (Long) values.get(CREDIT): null;
        this.debit = values.get(DEBIT) != null ? (Long) values.get(DEBIT): null;
        this.balance = values.get(BALANCE) != null ? (Long) values.get(BALANCE): null;
    }
}

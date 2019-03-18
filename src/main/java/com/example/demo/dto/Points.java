package com.example.demo.dto;


import lombok.Data;
import lombok.Value;

import java.io.Serializable;

public interface Points {
    public Long getAccountId();
    public String getAccountName();
    public Integer getTotalPoint();
}

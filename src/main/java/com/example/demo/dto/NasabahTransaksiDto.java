package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

public interface NasabahTransaksiDto{

    Long id();
    String nama();
    Long total();

}

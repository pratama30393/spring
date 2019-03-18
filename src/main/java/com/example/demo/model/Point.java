package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity@Data
public class Point implements Serializable {

    private static final long serialVersionUID = -136653301161511875L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long accountId;
    private Integer totalPoint;

    public Point() {
    }

    public Point(Long accountId, Integer totalPoint) {
        this.accountId = accountId;
        this.totalPoint = totalPoint;
    }
}

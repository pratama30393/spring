package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Transaksi implements Serializable {

    private static final long serialVersionUID = -4652968727232222694L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    private String description;

    private String debitCreditStatus;

    private Long amount;

    private Long balance;

   /* @ManyToOne
    @JoinColumn(name = "id")
    private Nasabah nasabah;*/

    public Transaksi() {
    }

    public Transaksi(Long accountId, Date transactionDate, String description, String debitCreditStatus, Long amount,Long balance) {
        this.accountId = accountId;
        this.transactionDate = transactionDate;
        this.description = description;
        this.debitCreditStatus = debitCreditStatus;
        this.balance = balance;
        this.amount = amount;
    }
}

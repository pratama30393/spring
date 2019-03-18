package com.example.demo.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Nasabah implements Serializable {

    private static final long serialVersionUID = -3781887609609178736L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "nama",nullable = false)
    private String nama;

   /* @OneToMany(targetEntity = Transaksi.class,mappedBy = "nasabah",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Transaksi> transaksis;*/

    public Nasabah() {
    }

    public Nasabah(Long id, String nama) {
        this.nama = nama;
        this.id = id;
    }
}

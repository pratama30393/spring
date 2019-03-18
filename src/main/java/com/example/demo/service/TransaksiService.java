package com.example.demo.service;

import com.example.demo.model.Transaksi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TransaksiService {

    Transaksi save(Transaksi transaksi);

    List<Transaksi> findAll();

    Page<Transaksi> findAllwithPage(Pageable pageable);

    List<Transaksi> findTransaksiByIdAndDate(Long accountId, Date startDate,Date endDate);

    List<Transaksi> findTransaksiByAccountId(Long accountId);

    Transaksi findTransaksiByid(Long id);
}

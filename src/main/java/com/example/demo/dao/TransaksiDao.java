package com.example.demo.dao;

import com.example.demo.model.Transaksi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.Date;
import java.util.List;

@Repository
public interface TransaksiDao extends JpaRepository<Transaksi,Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM TRANSAKSI WHERE ACCOUNT_ID = :accountId ORDER BY ID DESC LIMIT 1")
    public Transaksi findTransaksisByAccountIdOrderById(@Param("accountId") Long accountId);

    public List<Transaksi> findAllByAccountIdAndTransactionDateBetween(@Param("accountId") Long accountId, Date startDate,Date endDate);

    public List<Transaksi> findAllByAccountId(@Param("accountId") Long accountId);

    public Page<Transaksi> findAll(Pageable pageable);

    public Transaksi findTransaksiById(@Param("id") Long id);
}

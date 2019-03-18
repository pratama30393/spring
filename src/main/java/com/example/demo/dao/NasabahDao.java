package com.example.demo.dao;

import com.example.demo.dto.NasabahTransaksiDto;
import com.example.demo.dto.TabunganReport;
import com.example.demo.dto.TabunganReportDto;
import com.example.demo.model.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface NasabahDao extends JpaRepository<Nasabah,Long> {


    @Query(nativeQuery = true,value = "SELECT N.ID AS ID, N.NAMA AS NAMA ,IFNULL(SUM(T.AMOUNT ),0) AS TOTAL FROM NASABAH N LEFT JOIN TRANSAKSI T ON N.ID = T.ACCOUNT_ID GROUP BY N.ID")
    public List<NasabahTransaksiDto> listTransaksiNasabah();


    @Query(nativeQuery = true,value = "SELECT T.DESCRIPTION AS DESCRIPTION, T.TRANSACTION_DATE AS DATE," +
            "CASEWHEN (DEBIT_CREDIT_STATUS ='C',T.AMOUNT,0) AS CREDIT  ,CASEWHEN(DEBIT_CREDIT_STATUS ='D',T.AMOUNT,0) AS DEBIT ,T.BALANCE AS BALANCE " +
            "FROM NASABAH N LEFT JOIN TRANSAKSI T " +
            "ON N.ID = T.ACCOUNT_ID WHERE N.ID = :accountId AND T.TRANSACTION_DATE BETWEEN :start AND :end")
    public List<TabunganReport> listTabungan(@Param("accountId") Long accountId, @Param("start") Date startDate, @Param("end") Date endDate);
    //public List<TabunganReportDto> listTabungan(@Param("accountId") Long accountId, @Param("start") Date startDate, @Param("end") Date endDate);

    @Query(nativeQuery = true,value = "SELECT id FROM NASABAH WHERE id = :id")
    public Long findAccountId(@Param("id") Long accountId);
}

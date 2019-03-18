package com.example.demo.service;

import com.example.demo.dto.NasabahTransaksiDto;
import com.example.demo.dto.TabunganReport;
import com.example.demo.dto.TabunganReportDto;
import com.example.demo.model.Nasabah;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface NasabahService {

    Nasabah save(Nasabah nasabah);

    List<Nasabah> findAll();

    List<NasabahTransaksiDto> findAllTransaksiNasabah();

    List<TabunganReport> findTabunganByIdAndDate(Long accountId, Date start, Date end);

    /*List<NasabahTransaksiDto> findtransaksiNasabahById(Long accountId);*/
}

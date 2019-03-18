package com.example.demo.controller;


import com.example.demo.dto.NasabahTransaksiDto;
import com.example.demo.dto.TabunganReport;
import com.example.demo.export.ExportTabunganGenerator;
import com.example.demo.model.Nasabah;
import com.example.demo.service.NasabahService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NasabahController {

    @Autowired
    private NasabahService nasabahService;

    @GetMapping("/nasabah")
    public String nasabah(Model model, Nasabah nasabah){
        List<Nasabah> list = nasabahService.findAll();
        model.addAttribute("nasabah",list);

        return "nasabah";
    }

    @PostMapping("/nasabah")
    public String createNasabah(@Valid @ModelAttribute("nasabah") Nasabah nasabah, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "nasabah";
        }

        nasabahService.save(nasabah);

        List<Nasabah> list = nasabahService.findAll();
        model.addAttribute("nasabah",list);
        return "nasabah";
    }

    /*@GetMapping("/nasabaht?id=id")
    public String findTransaksiNasabah(@RequestParam("id") Long id, Model model){
        List<NasabahTransaksiDto> list = nasabahService.findAllTransaksiNasabah();
        model.addAttribute("transaksi",list);
        return "nasabah";
    }*/

    @PostMapping("/export")
    public ResponseEntity<InputStreamResource> excelCustomersReport(@RequestParam("aId")Long accountId, @RequestParam("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                               @RequestParam("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) throws IOException{
        List<TabunganReport> listData = nasabahService.findTabunganByIdAndDate(accountId,startDate,endDate);
        ByteArrayInputStream inputStream = ExportTabunganGenerator.createExcelTabungan(listData);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=tabungan.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }

}

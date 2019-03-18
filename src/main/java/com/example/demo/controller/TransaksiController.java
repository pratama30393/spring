package com.example.demo.controller;

import com.example.demo.model.Transaksi;
import com.example.demo.service.PointService;
import com.example.demo.service.TransaksiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TransaksiController {

    Logger log = LoggerFactory.getLogger(TransaksiController.class);

    @Autowired
    private TransaksiService transaksiService;

    @Autowired
    private PointService pointService;

    private static final Integer PAGE_SIZE = 10;

    private static final String REDIRECT_TO_MAIN_TRANSACTION = "/transaksi/1";

    @GetMapping("/transaksi")
    public ResponseEntity<Object> redirectToPage() throws URISyntaxException{
        URI main = new URI(REDIRECT_TO_MAIN_TRANSACTION);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(main);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/transaksi/{page}")
    public String mainTransaksi(@PathVariable(value = "page",required = false)int page, Model model){
        //List<Transaksi> list = transaksiService.findAll();
        PageRequest pageRequest = PageRequest.of(page -1,PAGE_SIZE);
        Page<Transaksi> transaksiPage = transaksiService.findAllwithPage(pageRequest);
        int getTotalPage = transaksiPage.getTotalPages();
        if(getTotalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,getTotalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }

        model.addAttribute("transaksi",transaksiPage.getContent());

        return "transaksi";
    }

    @PostMapping("/transaksi")
    public String createTransaksi(Model model , @Valid@ModelAttribute("transaksi") Transaksi transaksi){

        transaksi.setTransactionDate(new Date());
        Transaksi result = transaksiService.save(transaksi);
        if(result == null){
            model.addAttribute("transaksi",null);
            model.addAttribute("ERROR",true);
            return "transaksi";
        }

        pointService.save(transaksi);

        List<Transaksi> list = transaksiService.findAll();
        model.addAttribute("transaksi",list);

        return "transaksi";
    }

    @PostMapping("/search")
    public String findTransaction(Model model,@RequestParam(value = "aId",required = false)Long accountId , @RequestParam(name = "startDate",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                  @RequestParam(name = "endDate",required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate){
        if(accountId== null){
            return "transaksi";
        }
        /*if(startDate == null || endDate == null){
            startDate = new Date();
            endDate = new Date().set
        }*/
        List<Transaksi> list = transaksiService.findTransaksiByIdAndDate(accountId,startDate,endDate);
        model.addAttribute("transaksi",list);

        return "transaksi";
    }

    @GetMapping("/transaksi/nasabah")
    public String findTransaksiNasabah(@RequestParam("id")Long accountId,Model model){

        try {
            List<Transaksi> list = transaksiService.findTransaksiByAccountId(accountId);
            model.addAttribute("transaksi",list);
        }catch (Exception e){
            log.error("ERROR GET TRANSAKSI NASABAH ID :"+accountId);
            log.error("[ERROR]",e);
        }
        return "transaksi";
    }

}

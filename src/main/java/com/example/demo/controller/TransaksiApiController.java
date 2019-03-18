package com.example.demo.controller;

import com.example.demo.model.Transaksi;
import com.example.demo.service.TransaksiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "/api/transaksi",description = "transaksi nasabah",produces = "application/json")
@RequestMapping("/api/transaksi")
public class TransaksiApiController {

    Logger logger = LoggerFactory.getLogger(TransaksiApiController.class);

    @Autowired
    public TransaksiService transaksiService;

    @ApiOperation(value = "get transaksi",response = TransaksiApiController.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Transaksi Nasabah Retieved",response = TransaksiApiController.class),
            @ApiResponse(code = 500,message = "Server Error"),
            @ApiResponse(code = 404,message = "Transaksi Not Found")
    })
    @GetMapping(value = "/getTransaksi", produces = "application/json")
    public ResponseEntity<List<Transaksi>> getTransaksi(){
        List<Transaksi> list = transaksiService.findAll();
        return new ResponseEntity<List<Transaksi>>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "get transaksi by id transaksi",response = TransaksiApiController.class)
    @GetMapping(value = "/getTransaksiById/{id}",produces = "application/json")
    public ResponseEntity<Transaksi> getTransaksiById(@PathVariable("id")Long id){
        Transaksi transaksi = transaksiService.findTransaksiByid(id);
        return new ResponseEntity<Transaksi>(transaksi,HttpStatus.OK);
    }

}

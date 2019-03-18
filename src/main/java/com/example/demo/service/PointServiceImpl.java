package com.example.demo.service;

import com.example.demo.dao.PointDao;
import com.example.demo.dto.Points;
import com.example.demo.model.Point;
import com.example.demo.model.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PointServiceImpl implements PointService {
    private static final Integer point0 = 0;
    private static final Integer point1 = 1;
    private static final Integer point2 = 2;

    @Autowired
    public PointDao pointDao;

    @Override
    public List<Point> findAll() {
        return pointDao.findAll();
    }

    @Override
    public List<Points> findAllWithName() {
        return pointDao.findAllList();
    }
    @Override
    public Point save(Point point) {
        return pointDao.save(point);
    }

    @Override
    public Point save(Transaksi transaksi) {
        Point point = new Point();

        point.setAccountId(transaksi.getAccountId());
        if(transaksi.getDebitCreditStatus().trim().equalsIgnoreCase("D")){
            if(transaksi.getDescription().toLowerCase().contains("pulsa")){
                Integer resultPoint = logicPoint(transaksi.getAmount(),"pulsa");
                point.setTotalPoint(resultPoint);
                pointDao.save(point);
            }else
            if(transaksi.getDescription().toLowerCase().contains("listrik")){
                Integer resultPoint = logicPoint(transaksi.getAmount(),"listrik");
                point.setTotalPoint(resultPoint);
                pointDao.save(point);
            }else{
                point.setTotalPoint(0);
            }
            return  pointDao.save(point);
        }
        return null;
    }

    public Integer logicPoint(Long amount,String jenis){
        Double hasil1=0.0;
        Double hasil2=0.0;
        Double hasil3=0.0;
        Integer hasil =0,res1 = 0,res2 = 0,res3 = 0;
        if(jenis == "pulsa"){
            if(amount <= 10000 || amount > 10000){
                hasil1 = (double)(amount /1000);
                hasil1 = hasil1 * point0;
            }
            if(amount >10000 && amount <= 30000 || amount>30000){
                if(amount>30000){
                    hasil2 = (double)(20000 /1000);//fix 20 point
                }else {
                    hasil2 = (double)(amount - 10000) / 1000;
                }
                hasil2 = hasil2 * point1;
            }
            if(amount >30000){
                hasil3 = (double)(amount-30000)/1000;
                hasil3 = hasil3 * point2;
            }
        }else
        if(jenis == "listrik"){

            if(amount <= 50000 || amount >50000){
                hasil1 = (double)amount /2000 ;
                hasil1 = hasil1 * 0;
            }

            if(amount >50000 && amount <= 100000 || amount >100000) {
                if (amount > 100000) {
                    hasil2 = (double) 50000 / 2000;//fix 25 point
                }else{
                    hasil2 = (double) (amount - 50000) / 2000;
                }
                hasil2 = hasil2 * point1;
            }

            if(amount >100000){
                hasil3 = (double)(amount-100000)/2000;
                hasil3 = hasil3 * point2;
            }
        }
        hasil = hasil1.intValue() + hasil2.intValue()+ hasil3.intValue();
        return hasil;
    }
}

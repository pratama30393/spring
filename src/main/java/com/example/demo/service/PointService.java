package com.example.demo.service;

import com.example.demo.dto.Points;
import com.example.demo.model.Point;
import com.example.demo.model.Transaksi;

import java.util.List;

public interface PointService {
    List<Point> findAll();

    List<Points> findAllWithName();

    Point save(Point point);

    Point save(Transaksi transaksi);
}

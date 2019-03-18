package com.example.demo.dao;

import com.example.demo.dto.Points;
import com.example.demo.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointDao extends JpaRepository<Point,Long> {

    @Query(nativeQuery = true,value = "SELECT P.ACCOUNT_ID AS ACCOUNTID,N.NAMA AS ACCOUNTNAME, SUM(P.TOTAL_POINT) AS TOTALPOINT FROM POINT P LEFT JOIN NASABAH N ON P.ACCOUNT_ID = N.ID GROUP BY P.ACCOUNT_ID")
    public List<Points> findAllList();

}

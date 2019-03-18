package com.example.demo.controller;

import com.example.demo.dto.Points;
import com.example.demo.model.Point;
import com.example.demo.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PointController {

    @Autowired
    PointService pointService;

    @GetMapping("/point")
    public String pointIndex(Model model){
        List<Points> listData =  pointService.findAllWithName();
        model.addAttribute("point",listData);
        return "point";
    }
}

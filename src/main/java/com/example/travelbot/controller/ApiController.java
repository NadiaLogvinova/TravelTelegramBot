package com.example.travelbot.controller;

import com.example.travelbot.service.CityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private CityInfoService cityInfoService;



    @DeleteMapping("/api/cities/{cityName}")
    public void deleteCityInfo(@PathVariable String cityName) {

        cityInfoService.deleteCityInfoByCity(cityName);

    }

}

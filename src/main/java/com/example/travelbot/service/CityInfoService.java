package com.example.travelbot.service;

import com.example.travelbot.model.CityInfo;
import org.springframework.transaction.annotation.Transactional;


public interface CityInfoService {

    @Transactional(readOnly = true)
    String findInfoByCityIgnoreCase(String city);

    @Transactional
    void deleteCityInfoByCity(String city);
}

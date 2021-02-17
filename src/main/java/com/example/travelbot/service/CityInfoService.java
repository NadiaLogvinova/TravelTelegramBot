package com.example.travelbot.service;

import com.example.travelbot.tranfserobject.CityInfoTO;


public interface CityInfoService {

    String findInfoByCityIgnoreCase(String cityName);

    void deleteCityInfoByCity(String cityName);

    CityInfoTO createCityInfo(CityInfoTO cityInfoTO);

    CityInfoTO getCityInfoTO(String cityName);

    CityInfoTO updateCityInfo(String cityName, CityInfoTO cityInfoTO);
}

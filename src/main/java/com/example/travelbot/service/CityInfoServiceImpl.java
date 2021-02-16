package com.example.travelbot.service;

import com.example.travelbot.model.CityInfo;
import com.example.travelbot.repository.CityInfoRepository;
import com.example.travelbot.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityInfoServiceImpl implements CityInfoService {

    @Autowired
    private CityInfoRepository cityInfoRepository;

    @Override
    public String findInfoByCityIgnoreCase(String city) {
        if (StringUtil.isStringEmpty(city)) {
            return null;
        }

        String cityInfo = cityInfoRepository.findByCityIgnoreCase(city).map(CityInfo::getInfo).orElseGet(() -> null);
        return cityInfo;
    }

    @Override
    public void deleteCityInfoByCity(String city) {

        cityInfoRepository.deleteById(city);
    }
}

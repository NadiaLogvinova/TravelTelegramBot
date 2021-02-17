package com.example.travelbot.service;

import com.example.travelbot.exeption.handler.ExceptionMessage;
import com.example.travelbot.exeption.handler.exeption.DuplicateException;
import com.example.travelbot.exeption.handler.exeption.NotFoundException;
import com.example.travelbot.model.CityInfo;
import com.example.travelbot.repository.CityInfoRepository;
import com.example.travelbot.tranfserobject.CityInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service level of work with data.
 *
 * @author  n.logvinova
 */
@Service
@Transactional
public class CityInfoServiceImpl implements CityInfoService {

    @Autowired
    private CityInfoRepository cityInfoRepository;

    /**
     * Returns information about a city by given {@code cityName} (ignore case).
     * Returns {@code null}, if the city is not found.
     *
     * @param cityName - city name
     * @return {@link String} or {@code null}
     */
    @Transactional(readOnly = true)
    @Override
    public String findInfoByCityIgnoreCase(String cityName) {
        String cityInfo = cityInfoRepository.findByCityIgnoreCase(cityName).map(CityInfo::getInfo).orElseGet(() -> null);
        return cityInfo;
    }

    /**
     * Deletes {@link CityInfo} object by given {@code cityName}(ignore case).
     * Throws {@link NotFoundException}, if such {@link CityInfo} is not found.
     *
     * @param cityName - city name
     * @throws {@link NotFoundException}
     */
    @Override
    public void deleteCityInfoByCity(String cityName) {
        CityInfo byCityIgnoreCase = cityInfoRepository.findByCityIgnoreCase(cityName).orElseThrow(() -> new NotFoundException(cityName + ExceptionMessage.NOT_FOUND));
        cityInfoRepository.delete(byCityIgnoreCase);
    }

    /**
     * Creates new {@link CityInfo} object from data provided by {@code cityInfoTO}.
     * Returns appropriate {@link CityInfoTO} object.
     * Throws {@link DuplicateException}, if such city already exists (ignore case).
     *
     * @param cityInfoTO
     * @return {@link CityInfoTO}
     * @throws {@link DuplicateException}
     */
    @Override
    public CityInfoTO createCityInfo(CityInfoTO cityInfoTO) {

        String cityName = cityInfoTO.getCity();
        if (cityInfoRepository.existsByCityIgnoreCase(cityName)) {
            throw new DuplicateException(cityName + ExceptionMessage.DUPLICATED);
        }

        CityInfo cityInfo = convert(cityInfoTO);
        CityInfo savedCityInfo = cityInfoRepository.save(cityInfo);
        CityInfoTO savedCityInfoTO = convert(savedCityInfo);
        return savedCityInfoTO;
    }

    /**
     * Gets {@link CityInfo} by provided {@code cityName} (ignore case).
     * Returns appropriate {@link CityInfoTO} object.
     * Throws {@link NotFoundException}, if such city is not found.
     *
     * @param cityName - city name
     * @return {@link CityInfoTO}
     * @throws {@link NotFoundException}
     */
    @Transactional(readOnly = true)
    @Override
    public CityInfoTO getCityInfoTO(String cityName) {
        CityInfo cityInfo = cityInfoRepository.findByCityIgnoreCase(cityName).orElseThrow(() -> new NotFoundException(cityName + ExceptionMessage.NOT_FOUND));
        CityInfoTO cityInfoTO = convert(cityInfo);
        return cityInfoTO;
    }

    /**
     * Updates info about a city with given {@code cityName} (ignore case).
     * City name or/and city info (properties of {@link CityInfo} object) will be updated from {@code cityInfoTO} data.
     * Returns appropriate {@link CityInfoTO} object.
     * Throws {@link NotFoundException}, if the city with provided {@code cityName} is not exists.
     *
     * @param cityName - city name
     * @param cityInfoTO - info for update
     * @return {@link CityInfoTO} - updated info
     * @throws {@link NotFoundException}
     */
    @Override
    public CityInfoTO updateCityInfo(String cityName, CityInfoTO cityInfoTO) {
        CityInfo cityInfo = cityInfoRepository.findByCityIgnoreCase(cityName).orElseThrow(() -> new NotFoundException(cityName + ExceptionMessage.NOT_FOUND));
        cityInfo.setCity(cityInfoTO.getCity());
        cityInfo.setInfo(cityInfoTO.getInfo());
        CityInfoTO updated = convert(cityInfoRepository.save(cityInfo));
        return updated;
    }

    private CityInfo convert(CityInfoTO cityInfoTO) {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setCity(cityInfoTO.getCity());
        cityInfo.setInfo(cityInfoTO.getInfo());
        return cityInfo;
    }

    private CityInfoTO convert(CityInfo cityInfo) {
        CityInfoTO cityInfoTO = CityInfoTO.builder().city(cityInfo.getCity()).info(cityInfo.getInfo()).build();
        return cityInfoTO;
    }
}

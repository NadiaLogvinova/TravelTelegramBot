package com.example.travelbot.repository;

import com.example.travelbot.model.CityInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CityInfoRepository extends CrudRepository<CityInfo, String> {

    Optional<CityInfo> findByCityIgnoreCase(String city);

}

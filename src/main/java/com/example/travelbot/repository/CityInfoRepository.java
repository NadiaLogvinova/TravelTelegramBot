package com.example.travelbot.repository;

import com.example.travelbot.model.CityInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Contains methods of repository level of data access.
 *
 * @author n.logvinova
 */
public interface CityInfoRepository extends CrudRepository<CityInfo, String> {

    Optional<CityInfo> findByCityIgnoreCase(String city);

    boolean existsByCityIgnoreCase(String s);
}

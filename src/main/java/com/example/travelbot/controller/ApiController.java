package com.example.travelbot.controller;

import com.example.travelbot.service.CityInfoService;
import com.example.travelbot.tranfserobject.CityInfoTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * REST API to work with DB information.
 * Provides possibility to add, update,
 * delete and get information about cities and their attractions.
 *
 * @author n.logvinova
 */
@RestController
@RequestMapping("/api")
@Api(tags = "Provided operations to work with information about cities")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Finished successfully"),
        @ApiResponse(code = 400, message = "Bad request. Check provided data."),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal server error. Send notification to help desk.")
}
)
public class ApiController {

    @Autowired
    private CityInfoService cityInfoService;

    @ApiOperation(value = "Search info about a city")
    @GetMapping("/cities/{cityName}")
    public CityInfoTO getCityInfoTO(@PathVariable String cityName) {

        CityInfoTO cityInfoTO = cityInfoService.getCityInfoTO(cityName);

        return cityInfoTO;
    }

    @ApiOperation(value = "Add info about a city")
    @PostMapping("/cities")
    public CityInfoTO createCityInfo(@Valid @RequestBody CityInfoTO cityInfoTO) {

        return cityInfoService.createCityInfo(cityInfoTO);
    }

    @ApiOperation(value = "Update info about a city")
    @PutMapping("/cities/{cityName}")
    public CityInfoTO updateCityInfo(@PathVariable String cityName, @Valid @RequestBody CityInfoTO cityInfoTO) {

        return cityInfoService.updateCityInfo(cityName, cityInfoTO);
    }

    @ApiOperation(value = "Delete info about a city")
    @DeleteMapping("/cities/{cityName}")
    public void deleteCityInfo(@PathVariable String cityName) {

        cityInfoService.deleteCityInfoByCity(cityName);
    }

}

package com.example.travelbot.tranfserobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Transfer object for {@link com.example.travelbot.model.CityInfo}.
 *
 * @author n.logvinova
 */
@Setter
@Getter
public class CityInfoTO {

    @NotBlank
    @ApiModelProperty(notes = "Name of a city", required = true)
    private String city;

    @NotBlank
    @ApiModelProperty(notes = "Tourist information about a city", required = true)
    private String info;

}

package com.example.travelbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Object presentation of a record from SITY_INFO table.
 *
 * @author n.logvinova
 *
 */
@Entity
@Table(name = "city_info")
public class CityInfo {

    private String city;
    private String info;

    @Id
    @Column(name = "city", unique = true, nullable = false)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "info", nullable = false)
    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}

package com.example.travelbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Object presentation of a record from CITY_INFO table.
 *
 * @author n.logvinova
 */
@Entity
@Table(name = "city_info")
public class CityInfo {

    private Integer id;

    private String city;

    private String info;

    @Id
    @SequenceGenerator(name = "city_info_id_seq", sequenceName = "city_info_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "city_info_id_seq")
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

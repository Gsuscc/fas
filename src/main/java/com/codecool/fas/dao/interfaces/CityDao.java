package com.codecool.fas.dao.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface CityDao {

    CityDao getCity(String cityName);
}

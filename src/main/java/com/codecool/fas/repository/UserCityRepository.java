package com.codecool.fas.repository;

import com.codecool.fas.entity.City;
import com.codecool.fas.entity.UserCity;
import com.codecool.fas.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCityRepository extends JpaRepository<UserCity, Long> {

    boolean existsByCityAndUserInfo(City city, UserInfo user);
    List<UserCity> findAllByUserInfoIs(UserInfo user);

}

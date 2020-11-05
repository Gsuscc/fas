package com.codecool.fas.repository;

import com.codecool.fas.entity.City;
import com.codecool.fas.entity.UserCity;
import com.codecool.fas.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserCityRepository extends JpaRepository<UserCity, Long> {

    boolean existsByCityAndUserInfo(City city, UserInfo user);
    List<UserCity> findAllByUserInfoIs(UserInfo user);

    @Transactional
    @Modifying
    void deleteUserCityByCityIsAndUserInfoIs(City city, UserInfo user);
}

package com.codecool.fas.repository;

import com.codecool.fas.entity.City;
import com.codecool.fas.entity.UserCity;
import com.codecool.fas.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserCityRepository extends JpaRepository<UserCity, Long> {

    boolean existsByCityAndUserInfo(City city, UserInfo user);
    List<UserCity> findAllByUserInfoIs(UserInfo user);

    @Transactional
    @Modifying
    void deleteUserCityByCityIsAndUserInfoIs(City city, UserInfo user);

    @Transactional
    @Modifying
    @Query("UPDATE UserCity uc SET uc.notification = :isRequested WHERE uc.userInfo = :user AND uc.city = :city")
    void updateNotification(@Param("isRequested")boolean isRequested, @Param("user")UserInfo user,@Param("city") City city);
}

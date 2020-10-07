package com.codecool.fas.dao.interfaces;

import com.codecool.fas.model.TripAdvise;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TripAdviseDao {

    List<TripAdvise> getAdvices();

}

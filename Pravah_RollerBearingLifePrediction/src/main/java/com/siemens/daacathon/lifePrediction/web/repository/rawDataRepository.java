package com.siemens.daacathon.lifePrediction.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.siemens.daacathon.lifePrediction.web.model.rawData;


public interface rawDataRepository extends JpaRepository<rawData, Integer> {

}

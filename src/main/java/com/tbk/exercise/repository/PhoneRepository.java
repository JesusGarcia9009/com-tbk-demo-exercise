package com.tbk.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbk.exercise.models.PhoneModel;

public interface PhoneRepository extends JpaRepository<PhoneModel, Integer>{

}

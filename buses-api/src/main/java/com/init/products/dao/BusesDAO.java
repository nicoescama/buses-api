package com.init.products.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.products.entities.Bus;

public interface BusesDAO extends JpaRepository<Bus, Long> {

}

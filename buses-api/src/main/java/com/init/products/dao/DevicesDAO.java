package com.init.products.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.products.entities.Device;


public interface DevicesDAO extends JpaRepository<Device, Long> {

}

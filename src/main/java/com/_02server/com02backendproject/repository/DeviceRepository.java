package com._02server.com02backendproject.repository;

import com._02server.com02backendproject.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByDeviceNameIgnoreCaseContaining(String searchKeyword);
}

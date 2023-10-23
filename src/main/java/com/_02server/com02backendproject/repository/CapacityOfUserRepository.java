package com._02server.com02backendproject.repository;

import com._02server.com02backendproject.entities.CapacityOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CapacityOfUserRepository extends JpaRepository<CapacityOfUser, Long> {
    Optional<CapacityOfUser> findByUserCapacityId(Long userCapacityId);

    void deleteByUserCapacityId(Long userCapacityId);
}

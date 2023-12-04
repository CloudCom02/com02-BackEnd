package com._02server.com02backendproject.repository;

import com._02server.com02backendproject.entities.CapacityOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CapacityOfUserRepository extends JpaRepository<CapacityOfUser, Long> {
    Optional<CapacityOfUser> findByUserCapacityId(Long userCapacityId);
    List<CapacityOfUser> findAllByUser_UserId(Long userId);
    void deleteByUserCapacityId(Long userCapacityId);
    List<CapacityOfUser> findByUser_UserId(Long userId);
}

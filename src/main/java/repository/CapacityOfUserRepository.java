package repository;

import entities.CapacityOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacityOfUserRepository extends JpaRepository<CapacityOfUser, Long> {
}

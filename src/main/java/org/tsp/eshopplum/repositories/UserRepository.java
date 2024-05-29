package org.tsp.eshopplum.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.tsp.eshopplum.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

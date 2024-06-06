package org.tsp.eshopplum.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.tsp.eshopplum.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findByNameAndPassword(String name, String password);

}

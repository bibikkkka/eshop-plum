package org.tsp.eshopplum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tsp.eshopplum.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

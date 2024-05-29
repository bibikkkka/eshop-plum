package org.tsp.eshopplum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tsp.eshopplum.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

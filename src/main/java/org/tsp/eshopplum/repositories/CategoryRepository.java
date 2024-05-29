package org.tsp.eshopplum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tsp.eshopplum.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

}

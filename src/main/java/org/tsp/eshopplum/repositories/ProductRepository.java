package org.tsp.eshopplum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tsp.eshopplum.entities.Product;
import org.tsp.eshopplum.entities.Category;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByCategoriesEquals(Category category);
}

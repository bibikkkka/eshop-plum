package org.tsp.eshopplum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsp.eshopplum.entities.Category;
import org.tsp.eshopplum.entities.Product;
import org.tsp.eshopplum.repositories.CategoryRepository;
import org.tsp.eshopplum.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return productRepository.findProductsByCategoriesEquals(category);
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }
}

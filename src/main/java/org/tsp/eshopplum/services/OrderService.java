package org.tsp.eshopplum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsp.eshopplum.entities.*;
import org.tsp.eshopplum.entities.enums.OrderStatus;
import org.tsp.eshopplum.repositories.OrderItemRepository;
import org.tsp.eshopplum.repositories.OrderRepository;
import org.tsp.eshopplum.repositories.UserRepository;
import org.tsp.eshopplum.response.Basket;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    public Order doOrder(Basket basket) {
        Optional<User> user = userRepository.findByName(basket.getUsername());
        Order order = new Order(null, Instant.now(), OrderStatus.WAITING_PAYMENT, user.get());
        orderRepository.save(order);
        List<Product> products = basket.getItems();
        HashMap<Product, Integer> productMap = new HashMap<>();

        for (Product product : products) {
            productMap.put(product, productMap.getOrDefault(product, 0) + 1);
        }

        for (Product product : productMap.keySet()) {
            orderItemRepository.save(new OrderItem(order, product, productMap.get(product), product.getPrice()));
        }

        return order;
    }

}


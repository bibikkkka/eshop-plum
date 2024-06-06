package org.tsp.eshopplum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsp.eshopplum.entities.*;
import org.tsp.eshopplum.repositories.OrderItemRepository;
import org.tsp.eshopplum.repositories.OrderRepository;
import org.tsp.eshopplum.repositories.UserRepository;
import org.tsp.eshopplum.response.Basket;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    public Order doOrder(Basket basket){
        Optional<User> user = userRepository.findByName(basket.getUsername());
        System.out.println("\n########\n" + basket.getItems() + "\n########\n" );
        Order order = new Order(null, Instant.now(), OrderStatus.WAITING_PAYMENT, user.get());
        orderRepository.save(order);
        List<Product> products = basket.getItems();
        HashMap<Product, Integer> productMap = new HashMap<>();

        for (Product product: products){
            System.out.println("\n########\n" + product.getName() + "\n########\n" );
            productMap.put(product, productMap.getOrDefault(product, 0) + 1);
        }

        //List<OrderItem> currentOrder = new ArrayList<>();

        for (Product product : productMap.keySet()){

            //currentOrder.add(new OrderItem(order, product, productMap.get(product), product.getPrice()));
            orderItemRepository.save(new OrderItem(order, product, productMap.get(product), product.getPrice()));
        }
        //System.out.println(currentOrder);


        return order;
    }

}


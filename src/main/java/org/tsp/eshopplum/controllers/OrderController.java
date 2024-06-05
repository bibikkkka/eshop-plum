package org.tsp.eshopplum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tsp.eshopplum.entities.Order;
import org.tsp.eshopplum.entities.User;
import org.tsp.eshopplum.services.OrderService;
import org.tsp.eshopplum.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }

    @GetMapping(value = "/user/{id}") //по user_id
    public ResponseEntity<List<Order>> ordersByUserId(@PathVariable Long id){
        User user = userService.findById(id);

        List<Order> orders = user.getOrders();
        return orders.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(orders);

    }
}

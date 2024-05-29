package org.tsp.eshopplum.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.tsp.eshopplum.entities.*;
import org.tsp.eshopplum.entities.enums.Role;
import org.tsp.eshopplum.repositories.*;




@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User(null, "admin", "", "", "admin", Role.ADMIN);
        User user2 = new User(null, "Дмитрий Брикоткин", "dmitriybri@gmail.com", "85999999991", "pass", Role.USER);
        User user3 = new User(null, "Капкаева Галина", "zvezdochka@mail.ru", "85999559991", "pass", Role.USER);

        Order order1 = new Order(null, Instant.parse("2024-01-20T19:53:07Z"), OrderStatus.PAID, user2);
        Order order2 = new Order(null, Instant.parse("2023-07-19T03:42:10Z"), OrderStatus.SHIPPED, user2);
        Order order3 = new Order(null, Instant.parse("2023-10-20T21:21:22Z"), OrderStatus.WAITING_PAYMENT, user3);

        Category categoryFood = new Category(null, "Food");
        Category categoryToys = new Category(null, "Toys");
        Category categoryClothes = new Category(null, "Clothes");

        Product product1 = new Product(null, "Felix", "Dry cat food", 28.5, "");
        Product product2 = new Product(null, "Pedigree", "Dry dog food - 10 kg", 1190.0, "");
        Product product3 = new Product(null, "Bone toy", "Bone toy for active dogs", 1250.0, "");
        Product product4 = new Product(null, "Dog Jacket", "Funny dog coat with big mouse ears for warm winter ", 2200.0, "");
        Product product5 = new Product(null, "Cat Jacket", "Winter warm jacket for your cat", 1000.99, "");

        userRepository.saveAll(Arrays.asList(user2, user3));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        categoryRepository.saveAll(Arrays.asList(categoryFood, categoryToys, categoryClothes));
        productRepository.saveAll(Arrays.asList(product1, product2, product4, product3, product5));

        product1.getCategories().add(categoryFood);
        product2.getCategories().add(categoryFood);
        product3.getCategories().add(categoryToys);
        product4.getCategories().add(categoryClothes);
        product5.getCategories().add(categoryClothes);
        product5.getCategories().add(categoryToys);

        productRepository.saveAll(Arrays.asList(product1, product2, product4, product3, product5));

        OrderItem orderItem1 = new OrderItem(order1, product1, 4, product1.getPrice());
        OrderItem orderItem2 = new OrderItem(order1, product3, 2, product3.getPrice());
        OrderItem orderItem3 = new OrderItem(order2, product3, 1, product3.getPrice());
        OrderItem orderItem4 = new OrderItem(order3, product5, 1, product5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));

        /*Payment payment1 = new Payment(null, Instant.parse("2023-06-20T21:53:07Z"), order1);
        order1.setPayment(payment1);
        orderRepository.save(order1);*/
    }
}
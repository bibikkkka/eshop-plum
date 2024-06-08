package org.tsp.eshopplum.response;

import org.tsp.eshopplum.entities.Product;

import java.util.List;

public class Basket {
    private String username;
    private List<Product> items;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public Basket(String username, List<Product> items) {
        this.username = username;
        this.items = items;
    }

    public Basket() {
    }

    @Override
    public String toString() {
        return "Basket{" +
                "username='" + username + '\'' +
                ", items=" + items +
                '}';
    }
}

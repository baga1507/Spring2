package com.example.carts.model;

import com.example.api.ProductDto;
import com.example.api.ResourceNotFoundException;
import lombok.Data;

import java.util.*;

@Data
public class Cart {

    private List<CartItem> items;
    private int totalCost;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto product) {
        try {
            CartItem item = findItemByProductId(product.getId());
            item.changeQuantity(1);
        }
        catch (ResourceNotFoundException e) {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getCost(), product.getCost()));
        }
        recalculate();
    }

    public void decreaseItem(Long id) {
        CartItem item = findItemByProductId(id);
        item.changeQuantity(-1);
        if (item.getQuantity() <= 0) {
            items.remove(item);
        }
    }

    public void removeItem(Long id) {
        CartItem item = findItemByProductId(id);
        items.remove(item);
    }

    public CartItem findItemByProductId(Long id) {
        for (CartItem item : items) {
            if (item.getProductId().equals(id)) {
                return item;
            }
        }
        throw new ResourceNotFoundException("Product with id " + id + " is not found in cart");
    }

    private void recalculate() {
        totalCost = 0;
        for (CartItem item: items) {
            totalCost += item.getCost();
        }
    }

    public void clear() {
        items.clear();
        totalCost = 0;
    }
}

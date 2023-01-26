package com.example.Spring2.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "order_items")
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private int costPerProduct;

    @Column(name = "price")
    private int cost;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrderItem(Product product, Order order, int quantity, int costPerProduct, int cost) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.costPerProduct = costPerProduct;
        this.cost = cost;
    }

    public OrderItem(Long id, Product product, Order order, int quantity, int costPerProduct, int cost) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.costPerProduct = costPerProduct;
        this.cost = cost;
    }
}

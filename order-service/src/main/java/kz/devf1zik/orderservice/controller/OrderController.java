package kz.devf1zik.orderservice.controller;

import kz.devf1zik.orderservice.entity.Order;
import kz.devf1zik.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable UUID id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable UUID id) {
        return orderService.cancelOrder(id);
    }

    @PostMapping("/{id}/confirm")
    public Order confirmOrder(@PathVariable UUID id) {
        return orderService.confirmOrder(id);
    }

    @PostMapping("/{id}/ship")
    public Order shipOrder(@PathVariable UUID id) {
        return orderService.shipOrder(id);
    }

}
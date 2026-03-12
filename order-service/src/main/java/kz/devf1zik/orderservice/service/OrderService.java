package kz.devf1zik.orderservice.service;

import kz.devf1zik.common.dto.OrderItemDto;
import kz.devf1zik.common.dto.ReserveStockDto;
import kz.devf1zik.common.enums.OrderStatus;
import kz.devf1zik.common.exception.ResourceNotFoundException;
import kz.devf1zik.orderservice.entity.Order;
import kz.devf1zik.orderservice.kafka.ReserveStockProducer;
import kz.devf1zik.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ReserveStockProducer producer;

    public OrderService(OrderRepository orderRepository,
                        ReserveStockProducer producer) {
        this.orderRepository = orderRepository;
        this.producer = producer;
    }

    public Order createOrder(Order order) {

        order.setId(UUID.randomUUID());
        order.setStatus(OrderStatus.CREATED);

        Order savedOrder = orderRepository.save(order);

        List<OrderItemDto> items = savedOrder.getOrderItems()
                .stream()
                .map(i -> new OrderItemDto(
                        i.getProductId(),
                        i.getQuantity()
                ))
                .toList();

        ReserveStockDto dto = new ReserveStockDto(
                savedOrder.getId(),
                items
        );

        producer.sendReserveStockEvent(dto);

        return savedOrder;
    }

    public Order getOrder(UUID id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order cancelOrder(UUID id) {

        Order order = getOrder(id);

        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }

    public Order confirmOrder(UUID id) {

        Order order = getOrder(id);

        order.setStatus(OrderStatus.CONFIRMED);

        return orderRepository.save(order);
    }

    public Order shipOrder(UUID id) {

        Order order = getOrder(id);

        order.setStatus(OrderStatus.SHIPPED);

        return orderRepository.save(order);
    }

}
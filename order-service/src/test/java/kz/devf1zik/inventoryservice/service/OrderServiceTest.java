package kz.devf1zik.inventoryservice.service;

import kz.devf1zik.common.enums.OrderStatus;
import kz.devf1zik.orderservice.entity.Order;
import kz.devf1zik.orderservice.kafka.ReserveStockProducer;
import kz.devf1zik.orderservice.repository.OrderRepository;
import kz.devf1zik.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final OrderRepository orderRepository =
            Mockito.mock(OrderRepository.class);

    private final ReserveStockProducer producer =
            Mockito.mock(ReserveStockProducer.class);

    private final OrderService orderService =
            new OrderService(orderRepository, producer);

    @Test
    void createOrder_shouldSetStatusCreated() {

        Order order = new Order();
        order.setCustomerId(UUID.randomUUID());

        Mockito.when(orderRepository.save(Mockito.any()))
                .thenAnswer(i -> i.getArguments()[0]);

        Order savedOrder = orderService.createOrder(order);

        assertEquals(OrderStatus.CREATED, savedOrder.getStatus());

        Mockito.verify(producer, Mockito.times(1))
                .sendReserveStockEvent(Mockito.any());

    }

}
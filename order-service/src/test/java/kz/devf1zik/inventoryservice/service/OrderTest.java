package kz.devf1zik.inventoryservice.service;

import kz.devf1zik.common.enums.OrderStatus;
import kz.devf1zik.orderservice.entity.Order;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void orderStatusChange() {

        Order order = new Order();

        order.setId(UUID.randomUUID());
        order.setCustomerId(UUID.randomUUID());
        order.setStatus(OrderStatus.CREATED);

        order.setStatus(OrderStatus.CONFIRMED);

        assertEquals(OrderStatus.CONFIRMED, order.getStatus());

    }

}
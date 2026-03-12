package kz.devf1zik.inventoryservice.kafka;

import kz.devf1zik.common.dto.OrderItemDto;
import kz.devf1zik.common.dto.ReserveStockDto;
import kz.devf1zik.inventoryservice.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReserveStockConsumer {

    private final InventoryService inventoryService;

    public ReserveStockConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "reserve-stock", groupId = "inventory-group")
    public void handleReserveStock(ReserveStockDto dto) {

        for (OrderItemDto item : dto.getItems()) {

            inventoryService.reserveStock(
                    item.getProductId(),
                    item.getQuantity()
            );

        }

    }

}

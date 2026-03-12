package kz.devf1zik.orderservice.kafka;

import kz.devf1zik.common.dto.ReserveStockDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReserveStockProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ReserveStockProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendReserveStockEvent(ReserveStockDto dto) {

        kafkaTemplate.send(
                "reserve-stock",
                dto
        );

    }
}

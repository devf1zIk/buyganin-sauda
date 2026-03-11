package kz.devf1zik.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedDto {

    private UUID orderId;
    private UUID customerId;
    private List<OrderItemDto> items;
}

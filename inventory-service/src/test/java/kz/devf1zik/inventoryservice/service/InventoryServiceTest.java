package kz.devf1zik.inventoryservice.service;

import kz.devf1zik.inventoryservice.entity.Product;
import kz.devf1zik.inventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    private final InventoryService inventoryService =
            new InventoryService(productRepository);

    @Test
    void reserveStock_success() {

        UUID productId = UUID.randomUUID();

        Product product = new Product();
        product.setId(productId);
        product.setStock(10);

        Mockito.when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        boolean result = inventoryService.reserveStock(productId, 5);

        assertTrue(result);
        assertEquals(5, product.getStock());
    }

    @Test
    void reserveStock_notEnoughStock() {

        UUID productId = UUID.randomUUID();

        Product product = new Product();
        product.setId(productId);
        product.setStock(2);

        Mockito.when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        boolean result = inventoryService.reserveStock(productId, 5);

        assertFalse(result);
    }

}
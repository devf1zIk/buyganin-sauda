package kz.devf1zik.inventoryservice.controller;

import kz.devf1zik.inventoryservice.entity.Product;
import kz.devf1zik.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final InventoryService inventoryService;

    public ProductController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return inventoryService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return inventoryService.getProductById(id);
    }

}

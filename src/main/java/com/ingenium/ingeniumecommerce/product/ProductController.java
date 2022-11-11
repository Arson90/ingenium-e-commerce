package com.ingenium.ingeniumecommerce.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fexmon")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{productId}")
    ResponseEntity<ProductView> getProductById(@PathVariable final Long productId) {
        final ProductView productView = this.productService.findProductById(productId);
        return ResponseEntity.ok(productView);
    }

    @GetMapping("/products/productName/{productName}")
    ResponseEntity<ProductView> getProductByName(@PathVariable final String productName) {
        final ProductView productView = this.productService.findProductByName(productName);
        return ResponseEntity.ok(productView);
    }

    @GetMapping("/products")
    ResponseEntity<List<ProductView>> getProducts() {
        final List<ProductView> productList = this.productService.findAllProducts();
        if (!productList.isEmpty()) {
            return ResponseEntity.ok(productList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    ResponseEntity<ProductView> saveProduct(@RequestBody final ProductDTO productDTO) {
        final ProductView productView = this.productService.createProduct(productDTO);
        return ResponseEntity.ok(productView);
    }

    @PutMapping("/products/{productId}")
    ResponseEntity<ProductView> updateProduct(@RequestBody final ProductDTO productDTO, @PathVariable final Long productId) {
        final ProductView productView = this.productService.updateProduct(productDTO, productId);
        return ResponseEntity.ok(productView);
    }

    @DeleteMapping("/products/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable final Long productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}

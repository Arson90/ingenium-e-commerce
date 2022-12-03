package com.ingenium.ingeniumecommerce.product;

public final class ProductFactoryUtils {
    private ProductFactoryUtils() {
    }

    public static Product convertProductRequestDtoToProduct(final ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .productName(productRequestDTO.getProductName())
                .price(productRequestDTO.getPrice())
                .build();
    }

    public static ProductResponseDTO convertProductToProductResponseDto(final Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .build();
    }
}

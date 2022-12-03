package com.ingenium.ingeniumecommerce.orderEntry;

import com.ingenium.ingeniumecommerce.product.ProductFactoryUtils;

import java.util.Set;
import java.util.stream.Collectors;

public final class OrderEntryFactoryUtils {
    private OrderEntryFactoryUtils() {
    }

    public static Set<OrderEntryResponseDTO> convertOrderEntriesToOrderEntriesResponseDTO(final Set<OrderEntry> orderEntries) {
        return orderEntries.stream()
                .map(orderEntry -> OrderEntryResponseDTO.builder()
                        .id(orderEntry.getId())
                        .productResponseDTO(ProductFactoryUtils
                                .convertProductToProductResponseDto(orderEntry.getProduct()))
                        .quantity(orderEntry.getQuantity()).build())
                .collect(Collectors.toSet());
    }
}

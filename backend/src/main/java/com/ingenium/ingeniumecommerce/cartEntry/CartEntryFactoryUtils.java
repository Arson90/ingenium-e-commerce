package com.ingenium.ingeniumecommerce.cartEntry;

import com.ingenium.ingeniumecommerce.product.ProductFactoryUtils;

import java.util.Set;
import java.util.stream.Collectors;

public final class CartEntryFactoryUtils {
    private CartEntryFactoryUtils() {
    }

    public static Set<CartEntryResponseDTO> convertCartEntriesToCartEntriesResponseDTO(final Set<CartEntry> cartEntries) {
        return cartEntries.stream()
                .map(cartEntry -> CartEntryResponseDTO.builder()
                        .id(cartEntry.getId())
                        .productResponseDTO(ProductFactoryUtils
                                .convertProductToProductResponseDto(cartEntry.getProduct()))
                        .quantity(cartEntry.getQuantity()).build())
                .collect(Collectors.toSet());
    }
}

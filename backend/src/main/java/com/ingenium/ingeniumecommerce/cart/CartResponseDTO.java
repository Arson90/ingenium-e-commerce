package com.ingenium.ingeniumecommerce.cart;

import com.ingenium.ingeniumecommerce.cartEntry.CartEntryResponseDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class CartResponseDTO {
    private Long id;
    private Set<CartEntryResponseDTO> cartEntriesResponseDTO;
}

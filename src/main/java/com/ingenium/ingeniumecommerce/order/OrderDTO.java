package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.user.UserDTO;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderDTO {
    private UserDTO userDTO;
}

package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtility;
import com.ingenium.ingeniumecommerce.cart.Cart;
import com.ingenium.ingeniumecommerce.cart.CartCommandRepository;
import com.ingenium.ingeniumecommerce.cart.CartNotFoundException;
import com.ingenium.ingeniumecommerce.cartEntry.CartEntry;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderCommandRepository orderCommandRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final CartCommandRepository cartCommandRepository;

    public OrderServiceImpl(OrderCommandRepository orderCommandRepository, OrderQueryRepository orderQueryRepository, CartCommandRepository cartCommandRepository) {
        this.orderCommandRepository = orderCommandRepository;
        this.orderQueryRepository = orderQueryRepository;
        this.cartCommandRepository = cartCommandRepository;
    }

    @Override
    public OrderView findOrderById(final Long orderId) {
        return this.orderQueryRepository.findOrderById(orderId)
                .orElseThrow(() -> OrderNotFoundException.createForOrderId(orderId));
    }

    @Override
    public List<OrderView> findAllOrders() {
        return this.orderQueryRepository.findAllBy();
    }

    @Override
    @Transactional
    public OrderView createOrder(final OrderDTO orderDTO, final PaymentType paymentType, final String cartCookieId) {
        final Customer customer = CustomerFactoryUtils.createCustomer(orderDTO.getCustomerDTO());
        final Address address = AddressFactoryUtility.createAddress(orderDTO.getAddressDTO());
        customer.addAddressToCustomer(address);

        final Long cartId = Long.valueOf(cartCookieId);
        final Set<CartEntry> cartEntries = this.cartCommandRepository.findById(cartId)
                .map(Cart::getCartEntries)
                .orElseThrow(() -> CartNotFoundException.createForCartId(cartId));

        final Order order = new Order();
        order.addProductToOrderEntry(cartEntries);
        order.addCustomerAndAddressToOrder(customer);
        order.addPaymentTypeToOrder(paymentType);
        order.calculateTotalPrice();

        return this.orderCommandRepository.save(order).toOrderView();
    }
}

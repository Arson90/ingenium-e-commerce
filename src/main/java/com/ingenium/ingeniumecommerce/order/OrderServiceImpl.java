package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressDTO;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtility;
import com.ingenium.ingeniumecommerce.cart.CartNotFoundException;
import com.ingenium.ingeniumecommerce.cart.CartQueryRepository;
import com.ingenium.ingeniumecommerce.cart.CartView;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.enumeration.PaymentType;
import com.ingenium.ingeniumecommerce.productEntry.ProductEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderCommandRepository orderCommandRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final CartQueryRepository cartQueryRepository;

    public OrderServiceImpl(OrderCommandRepository orderCommandRepository, OrderQueryRepository orderQueryRepository, CartQueryRepository cartQueryRepository) {
        this.orderCommandRepository = orderCommandRepository;
        this.orderQueryRepository = orderQueryRepository;
        this.cartQueryRepository = cartQueryRepository;
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
    public OrderView createOrder(final OrderDTO orderDTO, final PaymentType paymentType, final String cartCookieId) {
        final Customer customer = toCustomer(orderDTO.getCustomerDTO());
        final Address address = toAddress(orderDTO.getAddressDTO());
        customer.addAddressToCustomer(address);

        final Long cartId = Long.valueOf(cartCookieId);
        final Set<ProductEntry> productEntries = this.cartQueryRepository.findCartById(cartId).map(CartView::getProductEntries)
                .orElseThrow(() -> CartNotFoundException.createForCartId(cartId));
        final Order order = OrderFactoryUtils.createOrder(customer, productEntries, paymentType);
        return this.orderCommandRepository.save(order).toOrderView();
    }

    private Customer toCustomer(final CustomerDTO customerDTO) {
        return CustomerFactoryUtils.createCustomer(customerDTO);
    }

    private Address toAddress(final AddressDTO addressDTO) {
        return AddressFactoryUtility.createAddress(addressDTO);
    }
}

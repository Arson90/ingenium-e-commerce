package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtils;
import com.ingenium.ingeniumecommerce.cartEntry.CartEntryRequestDTO;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.orderNotification.OrderNotification;
import com.ingenium.ingeniumecommerce.orderNotification.OrderNotificationCommandRepository;
import com.ingenium.ingeniumecommerce.product.Product;
import com.ingenium.ingeniumecommerce.product.ProductCommandRepository;
import com.ingenium.ingeniumecommerce.product.ProductNotFoundException;
import com.ingenium.ingeniumecommerce.security.auth.AuthenticationService;
import com.ingenium.ingeniumecommerce.user.User;
import com.ingenium.ingeniumecommerce.user.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderCommandRepository orderCommandRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final OrderNotificationCommandRepository orderNotificationCommandRepository;
    private final ProductCommandRepository productCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final AuthenticationService authenticationService;

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
    public List<Order> findMyOrders(final Customer customer) {
        return this.orderQueryRepository.findAllByCustomerId(customer.getId());
    }

    @Override
    @Transactional
    public OrderNotification createOrder(final OrderRequestDTO orderRequestDTO) {
        final OrderRequestWrapper orderRequestWrapper = prepareData(orderRequestDTO);
        Order order = new Order();
        order.addOrderDateToOrder(LocalDate.now());
        order.addCustomerToOrder(orderRequestWrapper.getCustomer());
        order.addCartEntriesToOrderEntries(orderRequestWrapper.getCartEntries());
        order.addPaymentTypeToOrder(orderRequestDTO.getPaymentType());
        order.calculateTotalPrice();
        this.orderCommandRepository.save(order);
        final OrderNotification orderNotification = OrderFactoryUtils.convertOrderToOrderNotification(order);
        this.orderNotificationCommandRepository.save(orderNotification);
        return orderNotification;
    }

    private OrderRequestWrapper prepareData(final OrderRequestDTO orderRequestDTO) {
        final Authentication authentication = this.authenticationService.getAuthentication();
        final Address address = AddressFactoryUtils.convertAddressRequestDtoToAddress(orderRequestDTO.getAddressRequestDTO());
        Customer customer = new Customer();

        if (!this.authenticationService.isAnonymousUser(authentication)) {
            Optional<User> user = this.userQueryRepository.findByUsername(authentication.getName());
            if (user.isPresent()) {
                customer = user.get().getCustomer();
                customer.addAddress(address);
            }
        } else {
            customer = CustomerFactoryUtils
                    .convertCustomerRequestDtoToCustomer(orderRequestDTO.getCustomerRequestDTO(), address);
        }

        final Map<Product, Integer> cartEntries = convertSetOfCartEntriesRequestDtoToMap(orderRequestDTO.getCartEntriesRequestDTO());
        return OrderRequestWrapper.builder()
                .customer(customer)
                .cartEntries(cartEntries)
                .build();
    }

    private Map<Product, Integer> convertSetOfCartEntriesRequestDtoToMap(final Set<CartEntryRequestDTO> cartEntriesRequestDTO) {
        return cartEntriesRequestDTO.stream()
                .collect(Collectors.toMap(cartEntryRequestDTO -> productCommandRepository.findById(cartEntryRequestDTO.getProductId())
                                .orElseThrow(() -> ProductNotFoundException.createForProductId(cartEntryRequestDTO.getProductId())),
                        CartEntryRequestDTO::getQuantity, (oldValue, newValue) -> oldValue));
    }
}

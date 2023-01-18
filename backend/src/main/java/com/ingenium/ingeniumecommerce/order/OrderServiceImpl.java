package com.ingenium.ingeniumecommerce.order;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtility;
import com.ingenium.ingeniumecommerce.cartEntry.CartEntryRequestDTO;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.product.Product;
import com.ingenium.ingeniumecommerce.product.ProductCommandRepository;
import com.ingenium.ingeniumecommerce.product.ProductNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderCommandRepository orderCommandRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final ProductCommandRepository productCommandRepository;

    public OrderServiceImpl(OrderCommandRepository orderCommandRepository, OrderQueryRepository orderQueryRepository, ProductCommandRepository productCommandRepository) {
        this.orderCommandRepository = orderCommandRepository;
        this.orderQueryRepository = orderQueryRepository;
        this.productCommandRepository = productCommandRepository;
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
    public OrderResponseDTO createOrder(final OrderRequestDTO orderRequestDTO) {
        final OrderRequestWrapper orderRequestWrapper = prepareData(orderRequestDTO);
        Order order = new Order();
        order.addCustomerToOrder(orderRequestWrapper.getCustomer());
        order.addCartEntriesToOrderEntries(orderRequestWrapper.getCartEntries());
        order.addPaymentTypeToOrder(orderRequestDTO.getPaymentType());
        order.calculateTotalPrice();
        order = this.orderCommandRepository.save(order);
        return OrderFactoryUtils.convertOrderToOrderResponseDto(order);
    }

    private OrderRequestWrapper prepareData(final OrderRequestDTO orderRequestDTO) {
        final Address address = AddressFactoryUtility
                .convertAddressRequestDtoToAddress(orderRequestDTO.getAddressRequestDTO());
        final Customer customer = CustomerFactoryUtils
                .convertCustomerRequestDtoToCustomer(orderRequestDTO.getCustomerRequestDTO(), address);
        final Map<Product, Integer> cartEntries = orderRequestDTO.getCartEntriesRequestDTO().stream()
                .collect(Collectors.toMap(cartEntryRequestDTO -> productCommandRepository.findById(cartEntryRequestDTO.getProductId())
                                .orElseThrow(() -> ProductNotFoundException.createForProductId(cartEntryRequestDTO.getProductId())),
                        CartEntryRequestDTO::getQuantity, (oldValue, newValue) -> oldValue));
        return OrderRequestWrapper.builder()
                .customer(customer)
                .cartEntries(cartEntries).build();
    }
}

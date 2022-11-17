package com.ingenium.ingeniumecommerce.product;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductCommandRepository productCommandRepository;
    private final ProductQueryRepository productQueryRepository;

    public ProductServiceImpl(ProductCommandRepository productCommandRepository, ProductQueryRepository productQueryRepository) {
        this.productCommandRepository = productCommandRepository;
        this.productQueryRepository = productQueryRepository;
    }

    @Override
    public ProductView findProductByName(final String productName) {
        return this.productQueryRepository.findByProductName(productName)
                .orElseThrow(() -> ProductNotFoundException.createForProductName(productName));
    }

    @Override
    public ProductView findProductById(final Long productId) {
        return this.productQueryRepository.findProductById(productId)
                .orElseThrow(() -> ProductNotFoundException.createForProductId(productId));
    }

    @Override
    public List<ProductView> findAllProducts() {
        return this.productQueryRepository.findAllBy();
    }

    @Override
    public ProductView createProduct(final ProductDTO productDTO) {
        final Product product = ProductFactoryUtility.createProduct(productDTO);
        return this.productCommandRepository.save(product).toProductView();
    }

    @Override
    @Transactional
    public ProductView updateProduct(final ProductDTO productDTO, final Long productId) {
        return this.productCommandRepository.findById(productId)
                .map(product -> product.updateCurrentProduct(productDTO).toProductView())
                .orElseThrow(() -> ProductNotFoundException.createForProductId(productId));
    }

    @Override
    public void deleteProduct(final Long productId) {
        this.productQueryRepository.deleteById(productId);
    }
}
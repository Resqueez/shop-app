package com.github.dawidsznajder.shop_app.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());

            return productRepository.save(existing);
                });
    }

    public Optional<Product> patchProduct(Long id, ProductPatchRequest patch) {
        return productRepository.findById(id).map(product -> {
            patch.getName().ifPresent(product::setName);
            patch.getDescription().ifPresent(product::setDescription);
            patch.getPrice().ifPresent(product::setPrice);

            return productRepository.save(product);
        });
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}

package ru.neZorinEgor.catering.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neZorinEgor.catering.errors.ProductNotFoundedException;
import ru.neZorinEgor.catering.model.Product;
import ru.neZorinEgor.catering.repository.ProductRepository;
import ru.neZorinEgor.catering.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long product_id) {
        return Optional.of(productRepository.findById(product_id).orElseThrow(ProductNotFoundedException::new));
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long product_id) {
        Optional<Product> product = findProductById(product_id);
        if (product.isPresent()){
            productRepository.deleteById(product_id);
        } else throw new ProductNotFoundedException();
    }
}

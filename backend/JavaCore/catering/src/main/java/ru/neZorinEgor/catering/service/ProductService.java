package ru.neZorinEgor.catering.service;

import org.springframework.stereotype.Service;
import ru.neZorinEgor.catering.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> findAllProduct();
    Optional<Product> findProductById(Long product_id);
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(Long product_id);
}

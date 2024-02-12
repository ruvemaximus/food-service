package ru.neZorinEgor.catering.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neZorinEgor.catering.errors.MyErrorResponse;
import ru.neZorinEgor.catering.errors.ProductNotFoundedException;
import ru.neZorinEgor.catering.model.Product;
import ru.neZorinEgor.catering.service.ProductService;

import java.util.List;
import java.util.Optional;

@Tag(name = "All methods")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long product_id){
        Optional<Product> product = productService.findProductById(product_id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<>("product successful created!", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return new ResponseEntity<>("product successful updated!", HttpStatus.OK);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long product_id){
        productService.deleteProductById(product_id);
        return new ResponseEntity<>("product succesfill deleted", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<MyErrorResponse> productNotFounded(ProductNotFoundedException e){
        MyErrorResponse response = new MyErrorResponse(
                HttpStatus.NOT_FOUND,
                "product with this id not exist!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

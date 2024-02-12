package ru.neZorinEgor.catering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neZorinEgor.catering.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

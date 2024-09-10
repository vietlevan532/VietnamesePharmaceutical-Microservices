package com.zezanziet.pharmaceutical.vn.ms.product_service.repositories;

import com.zezanziet.pharmaceutical.vn.ms.product_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

package com.zezanziet.pharmaceutical.vn.ms.product_service.repositories;

import com.zezanziet.pharmaceutical.vn.ms.product_service.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}

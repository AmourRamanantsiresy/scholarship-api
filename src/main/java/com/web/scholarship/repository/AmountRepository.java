package com.web.scholarship.repository;

import com.web.scholarship.models.Amount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountRepository extends JpaRepository<Amount, Long> {
}

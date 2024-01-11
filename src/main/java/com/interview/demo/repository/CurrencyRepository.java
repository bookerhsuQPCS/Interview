package com.interview.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.interview.demo.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    List<Currency> findByCode(@Param("code") String code);
    List<Currency> findByName(@Param("name") String name);
}

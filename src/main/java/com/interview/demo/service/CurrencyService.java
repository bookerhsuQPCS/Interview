package com.interview.demo.service;

import java.util.Optional;

import com.interview.demo.dto.*;
import com.interview.demo.entity.Currency;

public interface CurrencyService {
    public BaseResponse add(AddRequest request);
    public BaseResponse del(DelRequest request);
    public BaseResponse update(UpdateRequest request);
    public QueryResponse findAll();
    public QueryResponse queryByCode(QueryRequest request);
    public Optional<Currency> queryByCode(String code);
    public Optional<Currency> queryById(Long id);
}

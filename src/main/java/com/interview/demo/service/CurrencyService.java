package com.interview.demo.service;

import com.interview.demo.dto.*;

public interface CurrencyService {
    public BaseResponse add(AddRequest request);
    public BaseResponse del(DelRequest request);
    public BaseResponse update(UpdateRequest request);
    public QueryResponse query(QueryRequest request);
}

package com.interview.demo.service;

import com.interview.demo.dto.QueryCoindeskRequest;
import com.interview.demo.dto.QueryCoindeskResponse;

public interface CoindeskService {
    public QueryCoindeskResponse query(QueryCoindeskRequest request);
}

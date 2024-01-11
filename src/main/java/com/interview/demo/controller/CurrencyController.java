package com.interview.demo.controller;

import com.interview.demo.dto.AddRequest;
import com.interview.demo.dto.BaseResponse;
import com.interview.demo.dto.DelRequest;
import com.interview.demo.dto.QueryRequest;
import com.interview.demo.dto.QueryResponse;
import com.interview.demo.dto.UpdateRequest;
import com.interview.demo.service.CurrencyService;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api("Currency CURD Controller")
@Validated
@Slf4j
@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAdd(@Valid @RequestBody AddRequest request) throws Exception {
        return currencyService.add(request);
    }

    @PostMapping(value = "/del", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getDel(@Valid @RequestBody DelRequest request) throws Exception {
        return currencyService.del(request);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getUpdate(@Valid @RequestBody UpdateRequest request) throws Exception {
        return currencyService.update(request);
    }

    @PostMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public QueryResponse getQuery(@RequestBody QueryRequest request) throws Exception {
        return currencyService.query(request);
    }
}

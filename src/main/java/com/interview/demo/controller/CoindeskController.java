package com.interview.demo.controller;

import io.swagger.annotations.Api;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.demo.dto.QueryCoindeskRequest;
import com.interview.demo.dto.QueryCoindeskResponse;
import com.interview.demo.service.CoindeskService;

@Api("Coindesk API Controller")
@Validated
@Slf4j
@RestController
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @PostMapping(value = "/getCoindesk", produces = MediaType.APPLICATION_JSON_VALUE)
    public QueryCoindeskResponse getCoindesk(@Valid @RequestBody QueryCoindeskRequest request) throws Exception {

        return coindeskService.query(request);
    }
}

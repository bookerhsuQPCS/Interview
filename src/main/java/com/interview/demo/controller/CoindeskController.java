package com.interview.demo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.demo.dto.QueryCoindeskResponse;
import com.interview.demo.service.CoindeskService;

@Api("Coindesk API Controller")
@Validated
@Slf4j
@RestController
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @PostMapping(value = "/getCoindeskRaw", produces = MediaType.APPLICATION_JSON_VALUE)
    public QueryCoindeskResponse getCoindeskRaw() throws Exception {

        return coindeskService.query();
    }

    @PostMapping(value = "/getCoindesk", produces = MediaType.APPLICATION_JSON_VALUE)
    public QueryCoindeskResponse getCoindesk() throws Exception {

        return coindeskService.query();
    }
}

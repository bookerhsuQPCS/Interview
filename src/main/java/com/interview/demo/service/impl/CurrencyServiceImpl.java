package com.interview.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.demo.dto.*;
import com.interview.demo.entity.Currency;
import com.interview.demo.repository.CurrencyRepository;
import com.interview.demo.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

	@Override
	public BaseResponse add(AddRequest request) {
		Currency currency = new Currency();
		BaseResponse response = new BaseResponse();

		currency.setCode(request.getCode());
		currency.setName(request.getName());
		currencyRepository.save(currency);

        response.setStatus("Success");
        return response;
	}

	@Override
	public BaseResponse del(DelRequest request) {
		BaseResponse response = new BaseResponse();

		if (!currencyRepository.existsById(request.getId())) {
			response.setStatus("Fail, Record not exists.");
	        return response;
		}

		currencyRepository.deleteById(request.getId());

        response.setStatus("Success");
        return response;
	}

	@Override
	public BaseResponse update(UpdateRequest request) {
		BaseResponse response = new BaseResponse();

		if (!currencyRepository.existsById(request.getId())) {
			response.setStatus("Fail, Record not found.");
	        return response;
		}
		
		Currency currency = new Currency();
		currency.setId(request.getId());
		currency.setCode(request.getCode());
		currency.setName(request.getName());
		currencyRepository.save(currency);

        response.setStatus("Success");
        return response;
	}

	@Override
	public QueryResponse query(QueryRequest request) {
		QueryResponse response = new QueryResponse();
		
		if (request.getId() == null 
				&& StringUtils.isBlank(request.getCode())
				&& StringUtils.isBlank(request.getName())) {
			response.setStatus("Fail, 查詢條件不能空白.");
			return response;
		}

		if (request.getId() != null) {
			Optional<Currency> optCurrency = currencyRepository.findById(request.getId());
			if (optCurrency.isPresent()) {
				List<Currency> list = new ArrayList<>();
				list.add(optCurrency.get());
				response.setCurrencyList(list);
				response.setStatus("Success");
			} else {
				response.setStatus("Fail, record not found.");
			}
		} else if (StringUtils.isNotBlank(request.getCode())) {
			List<Currency> list = currencyRepository.findByCode(request.getCode());
			if (list.isEmpty()) {
				response.setStatus("Fail, record not found.");
			} else {
				response.setStatus("Success");
				response.setCurrencyList(list);
			}
		} else if (StringUtils.isNotEmpty(request.getName())) {
			List<Currency> list = currencyRepository.findByName(request.getName());
			if (list.isEmpty()) {
				response.setStatus("Fail, record not found.");
			} else {
				response.setStatus("Success");
				response.setCurrencyList(list);
			}
		}

        return response;
	}

	@Override
	public Optional<Currency> queryByCode(String code) {
		List<Currency> list = currencyRepository.findByCode(code);
		if (list.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(list.get(0));
		}
	}
	
}

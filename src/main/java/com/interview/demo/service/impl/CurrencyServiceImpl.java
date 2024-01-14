package com.interview.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.interview.demo.dto.*;
import com.interview.demo.entity.Currency;
import com.interview.demo.repository.CurrencyRepository;
import com.interview.demo.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

	@Override
    @Transactional
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
    @Transactional
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
    @Transactional
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
	public QueryResponse findAll() {
		List<Currency> list = currencyRepository.findAll();
		QueryResponse response = new QueryResponse();
		response.setStatus("Success");
		response.setCurrencyList(list);
		return response;
	}
	
	@Override
	public QueryResponse queryByCode(QueryRequest request) {
		List<Currency> list = currencyRepository.findByCode(request.getCode());
		QueryResponse response = new QueryResponse();
		response.setStatus("Success");
		response.setCurrencyList(list);
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
	
	@Override
	public Optional<Currency> queryById(Long id) {
		return currencyRepository.findById(id);
	}
	
}

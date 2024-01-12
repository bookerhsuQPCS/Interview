package com.interview.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.interview.demo.dto.AddRequest;
import com.interview.demo.dto.BaseResponse;
import com.interview.demo.dto.DelRequest;
import com.interview.demo.dto.QueryRequest;
import com.interview.demo.dto.UpdateRequest;
import com.interview.demo.entity.Currency;
import com.interview.demo.repository.CurrencyRepository;
import com.interview.demo.service.impl.CurrencyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

	@InjectMocks
	CurrencyServiceImpl service;

	@Mock
	CurrencyRepository dao;

	@Test
	void testCreateCurrency() {
		AddRequest addCurrency = new AddRequest();
		addCurrency.setCode("YNC");
		addCurrency.setName("測試幣");

		service.add(addCurrency);

		Currency currency = new Currency();
		currency.setCode(addCurrency.getCode());
		currency.setName(addCurrency.getName());

		verify(dao, times(1)).save(currency);
	}

	@Test
	void testUpdateCurrency() {

		UpdateRequest existingRequest = new UpdateRequest();
		existingRequest.setId(1L);
		existingRequest.setCode("YYA");
		existingRequest.setName("TESTNAME");

		when(dao.existsById(existingRequest.getId())).thenReturn(true);

		BaseResponse updateResult = service.update(existingRequest);

		assertEquals(updateResult.getStatus(), "Success");
		verify(dao, times(1)).existsById(existingRequest.getId());

		Currency existingCurrency = new Currency();
		existingCurrency.setId(existingRequest.getId());
		existingCurrency.setCode(existingRequest.getCode());
		existingCurrency.setName(existingRequest.getName());

		verify(dao, times(1)).save(existingCurrency);
	}

	@Test
	void testUpdateUser_UserNotFound() {

		UpdateRequest nonExistingRequest = new UpdateRequest();
		nonExistingRequest.setId(99L);
		nonExistingRequest.setCode("NON");
		nonExistingRequest.setName("NONE");

		when(dao.existsById(nonExistingRequest.getId())).thenReturn(false);

		BaseResponse updateResult = service.update(nonExistingRequest);

		assertEquals(updateResult.getStatus(), "Fail, Record not found.");
		verify(dao, times(1)).existsById(nonExistingRequest.getId());

		Currency nonExistingCurrency = new Currency();
		nonExistingCurrency.setId(nonExistingRequest.getId());
		nonExistingCurrency.setCode(nonExistingRequest.getCode());
		nonExistingCurrency.setName(nonExistingRequest.getName());

		verify(dao, never()).save(nonExistingCurrency);
	}

	@Test
	void testQueryCurrencyById() {
		QueryRequest qryCurrency = new QueryRequest();
		qryCurrency.setId(26L);

		service.query(qryCurrency);

		verify(dao, times(1)).findById(qryCurrency.getId());
	}

	@Test
	void testQueryCurrencyByCode() {
		QueryRequest qryCurrency = new QueryRequest();
		qryCurrency.setCode("YCC");

		service.query(qryCurrency);

		verify(dao, times(1)).findByCode(qryCurrency.getCode());
	}

	@Test
	void testQueryCurrencyByName() {
		QueryRequest qryCurrency = new QueryRequest();
		qryCurrency.setName("測試幣");

		service.query(qryCurrency);

		verify(dao, times(1)).findByName(qryCurrency.getName());
	}

	@Test
	void testDeleteCurrency() {

		long existingCurrencyrId = 10L;
		DelRequest existingCurrency = new DelRequest();
		existingCurrency.setId(existingCurrencyrId);

		when(dao.existsById(existingCurrencyrId)).thenReturn(true);

		// Act
		BaseResponse deleteResult = service.del(existingCurrency);

		// Assert
		assertEquals(deleteResult.getStatus(), "Success");
		verify(dao, times(1)).existsById(existingCurrencyrId);
		verify(dao, times(1)).deleteById(existingCurrencyrId);
	}

	@Test
	void testDeleteUser_UserNotFound() {

		long nonExistingCurrencyrId = 2L;
		DelRequest nonExistingCurrency = new DelRequest();
		nonExistingCurrency.setId(nonExistingCurrencyrId);

		when(dao.existsById(nonExistingCurrencyrId)).thenReturn(false);

		// Act
		BaseResponse deleteResult = service.del(nonExistingCurrency);

		// Assert
		assertEquals(deleteResult.getStatus(), "Fail, Record not exists.");
		verify(dao, times(1)).existsById(nonExistingCurrencyrId);
		verify(dao, never()).deleteById(nonExistingCurrencyrId);
	}

}

package com.interview.demo.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.interview.demo.dto.AddRequest;
import com.interview.demo.dto.BaseResponse;
import com.interview.demo.dto.DelRequest;
import com.interview.demo.dto.QueryResponse;
import com.interview.demo.dto.UpdateRequest;
import com.interview.demo.entity.Currency;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CurrencyServiceJunitTest {

    @Autowired
    private CurrencyService currencyService;
    
    private Long addedCurrencyId;

    @Test
    @Order(1)
    void testGetCurrencyByCode() {
    	QueryResponse respose = currencyService.findAll();
    	assertNotNull(respose);
        assertFalse(respose.getCurrencyList().isEmpty());
        
        System.out.println("Response : " + respose.toString());
    }

    @Test
    @Order(2)
    void testAddCurrency() {
        AddRequest addCurrency = new AddRequest();
        addCurrency.setCode("YNC");
        addCurrency.setName("測試幣");
        BaseResponse addResult = currencyService.add(addCurrency);

        assertNotNull(addResult.getStatus());
        assertEquals(addResult.getStatus(), "Success");

        Optional<Currency> optCurrency = currencyService.queryByCode("YNC");

        assertNotNull(optCurrency.orElse(null));
        assertEquals(optCurrency.get().getName(), "測試幣");
        
        addedCurrencyId = optCurrency.get().getId();
        System.out.println("Response addedCurrencyId: " + addedCurrencyId);
    }

    @Test
    @Order(3)
    void testUpdateCurrency() {
    	Optional<Currency> existingCurrency = currencyService.queryById(addedCurrencyId);
    	assertNotNull(existingCurrency.orElse(null));

    	Currency currency = existingCurrency.get();
        // Update details
		UpdateRequest existingRequest = new UpdateRequest();
		existingRequest.setId(currency.getId());
		existingRequest.setCode("DBD");
		existingRequest.setName("度比測試幣");

        currencyService.update(existingRequest);

        // Verify the changes
        Optional<Currency> updateCurrency = currencyService.queryById(existingRequest.getId());
        assertNotNull(updateCurrency.orElse(null));
        assertEquals("DBD", updateCurrency.get().getCode());
        assertEquals("度比測試幣", updateCurrency.get().getName());
        
        System.out.println("Response : " + updateCurrency.get().toString());
    }

    @Test
    @Order(4)
    void testDeleteCurrency() {
        // Assuming you have a method in UserService to retrieve a user by ID
        Optional<Currency> deleteCurrency = currencyService.queryById(addedCurrencyId);
        assertNotNull(deleteCurrency.orElse(null));

        DelRequest request = new DelRequest();
        request.setId(deleteCurrency.get().getId());
        currencyService.del(request);

        // Verify that the Currency has been deleted
        Optional<Currency> nonExistsCurrency = currencyService.queryById(deleteCurrency.get().getId());
        assertNull(nonExistsCurrency.orElse(null));
    }
}
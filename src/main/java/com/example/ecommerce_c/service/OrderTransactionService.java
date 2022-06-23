package com.example.ecommerce_c.service;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.ecommerce_c.domain.OrderTransaction;
import com.example.ecommerce_c.domain.OrderTransactionStatus;


/**
 * 
 * 
 * @author hvthinh
 *
 */
@Service
public class OrderTransactionService {
//	@Autowired
//	private RestTemplate restTemplate;
//	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	private static final String URL = "http://153.127.48.168:8080/sample-credit-card-web-api/credit-card/payment";
	
	/**
	 * @param orderTransaction
	 * @return 決済状態情報.
	 */
	public OrderTransactionStatus transacting(OrderTransaction orderTransaction) {
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			OrderTransactionStatus orderTransactionStatus = restTemplate.postForObject(URL, orderTransaction, OrderTransactionStatus.class);
			return orderTransactionStatus;
	    }
	    catch (HttpClientErrorException e) { // (1)
	        throw e;
	    }
	    catch (HttpServerErrorException e) { // (2)
	        throw e;
	    }
	}
}

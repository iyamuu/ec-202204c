package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
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
	@Autowired
	private RestTemplate restTemplate;
	

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	private static final String URL = "http://153.127.48.168:8080/sample-credit-card-web-api/credit-card/payment";
	
	/**
	 * @param orderTransaction
	 * @return 決済状態情報.
	 */
	public OrderTransactionStatus transacting(OrderTransaction orderTransaction) {
		OrderTransactionStatus orderTransactionStatus = restTemplate.getForObject(URL, OrderTransactionStatus.class, orderTransaction);
		System.out.println(orderTransactionStatus);
		return orderTransactionStatus;
	}
}

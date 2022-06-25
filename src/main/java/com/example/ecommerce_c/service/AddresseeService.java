package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Addressee;
import com.example.ecommerce_c.repository.AddresseeRepository;

/**
 * 宛先情報に関する操作を行うServiceクラス.
 * 
 * @author teranishidaina
 *
 */
@Service
public class AddresseeService {
	
	@Autowired
	private AddresseeRepository addresseeRepository;
	
	
	/**
	 * あるユーザの宛先情報を取得する.
	 * 
	 * @param userId 取得したいユーザのID
	 * @return　宛先情報
	 */
	public Addressee getUserAddressee(Integer userId) {
		return addresseeRepository.findOneByUserId(userId);
	}
}

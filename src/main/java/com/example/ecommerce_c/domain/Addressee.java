package com.example.ecommerce_c.domain;

/**
 * お届け先情報を表すドメインクラス.
 * 
 * @author daina.teranishi
 *
 */
public class Addressee {

	/** ID */
	private Integer id;
	/** ユーザーID */
	private Integer userId;
	/** 届け先の名前 */
	private String name;
	/** 郵便番号 */
	private String zipCode;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "Addressee [id=" + id + ", userId=" + userId + ", name=" + name + ", zipCode=" + zipCode + ", address="
				+ address + ", telephone=" + telephone + "]";
	}
}

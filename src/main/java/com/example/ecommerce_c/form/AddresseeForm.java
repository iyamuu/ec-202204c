package com.example.ecommerce_c.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 宛先情報のformクラス
 * 
 * @author teranishidaina
 *
 */
public class AddresseeForm {

	/** 宛先の名前 */
	@NotEmpty(message = "名前を入力してください")
	private String name;

	/** 郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号を入力してください (ハイフンあり）")
	private String zipCode;

	/** 住所 */
	@NotEmpty(message = "住所を入力してください (建物名まで)")
	private String address;

	/** 電話番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message = "電話番号を入力してください (ハイフンあり)")
	private String telephone;

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
		return "AddresseeForm [name=" + name + ", zipCode=" + zipCode + ", address=" + address + ", telephone="
				+ telephone + "]";
	}

}

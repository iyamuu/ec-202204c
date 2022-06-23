package com.example.ecommerce_c.domain;

import java.util.List;

/**
 * 商品を扱うドメイン.
 * 
 * @author hvthinh
 *
 */
public class Item {
	/** 商品ID */
	private Integer id;
	/** 商品の名前 */
	private String name;
	/** 商品詳細情報 */
	private String description;
	/** Mサイズの値段 */
	private Integer priceM;
	/** Lサイズの値段 */
	private Integer priceL;
	/** 画像パス */
	private String imagePath;
	/** 対象年齢 */
	private Integer targetAge;
	/** 性別 */
	private String gender;
	/** 削除フラグ */
	private Boolean deleted;
	/** トッピングリスト */
	private List<Topping> toppingList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<Topping> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

	public Integer getTargetAge() {
		return targetAge;
	}

	public void setTargetAge(Integer targetAge) {
		this.targetAge = targetAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", priceM=" + priceM + ", priceL="
				+ priceL + ", imagePath=" + imagePath + ", targetAge=" + targetAge + ", gender=" + gender + ", deleted="
				+ deleted + ", toppingList=" + toppingList + "]";
	}

}

package com.example.ecommerce_c.domain;

/**
 * 絞り込みたい条件を表すドメインクラス.
 * 
 * @author daina.teranishi
 *
 */
public class GiftInformation {

	/** ID */
	private Integer id;
	/** ユーザーID */
	private Integer userId;
	/** 年齢の下限 */
	private Integer lowerAge;
	/** 年齢の上限 */
	private Integer upperAge;
	/** 性別 */
	private String gender;
	/** 予算の下限 */
	private Integer lowerBudget;
	/** 予算の上限 */
	private Integer upperBudget;

	
	//初期の絞り込み情報は全データが対象になるように設定する
	public GiftInformation() {

		this.lowerAge = 0;
		this.upperAge = 100;
		
		this.lowerBudget = 0;
		this.upperBudget = 9999999;

	}

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

	public Integer getLowerAge() {
		return lowerAge;
	}

	public void setLowerAge(Integer lowerAge) {
		this.lowerAge = lowerAge;
	}

	public Integer getUpperAge() {
		return upperAge;
	}

	public void setUpperAge(Integer upperAge) {
		this.upperAge = upperAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getLowerBudget() {
		return lowerBudget;
	}

	public void setLowerBudget(Integer lowerBudget) {
		this.lowerBudget = lowerBudget;
	}

	public Integer getUpperBudget() {
		return upperBudget;
	}

	public void setUpperBudget(Integer upperBudger) {
		this.upperBudget = upperBudger;
	}

	@Override
	public String toString() {
		return "GitInformation [id=" + id + ", userId=" + userId + ", lowerAge=" + lowerAge + ", upperAge=" + upperAge
				+ ", gender=" + gender + ", lowerBudget=" + lowerBudget + ", upperBudger=" + upperBudget + "]";
	}
}

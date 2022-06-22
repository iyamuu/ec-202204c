package com.example.ecommerce_c.form;

/**
 * 絞り込み情報に関するformクラス
 * 
 * @author teranishidaina
 *
 */
public class GiftInfoForm {
	
	/** 年齢の下限 */
	private Integer lowerAge;
	/** 年齢の上限 */
	private Integer upperAge;
	/** 性別 */
	private String gender;
	/** 予算の下限 */
	private Integer lowerBudget;
	/** 予算の上限 */
	private Integer upperBudger;

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

	public Integer getUpperBudger() {
		return upperBudger;
	}

	public void setUpperBudger(Integer upperBudger) {
		this.upperBudger = upperBudger;
	}

	@Override
	public String toString() {
		return "GiftInfoForm [lowerAge=" + lowerAge + ", upperAge=" + upperAge + ", gender=" + gender + ", lowerBudget="
				+ lowerBudget + ", upperBudger=" + upperBudger + "]";
	}
}

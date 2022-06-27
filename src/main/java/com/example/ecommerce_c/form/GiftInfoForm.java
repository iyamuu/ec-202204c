package com.example.ecommerce_c.form;

import javax.validation.constraints.NotNull;

/**
 * 絞り込み情報に関するformクラス
 * 
 * @author teranishidaina
 *
 */

public class GiftInfoForm {
	
	/** 年齢の下限 */
	@NotNull(message = "対象の年齢を指定してください")
	private Integer lowerAge;
	/** 年齢の上限 */
	@NotNull(message = "対象の年齢を指定してください")
	private Integer upperAge;
	/** 性別 */
	@NotNull(message = "男の子か女の子かを指定してください")
	private String gender;
	/** 予算の下限 */
	@NotNull(message = "ご予算を教えてください")
	private Integer lowerBudget;
	/** 予算の上限 */
	@NotNull(message = "ご予算を教えてください")
	private Integer upperBudget;

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
		return "GiftInfoForm [lowerAge=" + lowerAge + ", upperAge=" + upperAge + ", gender=" + gender + ", lowerBudget="
				+ lowerBudget + ", upperBudger=" + upperBudget + "]";
	}
}

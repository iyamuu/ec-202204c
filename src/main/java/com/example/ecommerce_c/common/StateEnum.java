package com.example.ecommerce_c.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum StateEnum {
	
	PRE_ORDER(0, "注文前"),
	NOT_PAYMENT(1, "未入金"),
	DONE_PAYMENT(2, "入金済"),
	DONE_DELIVER(3, "発送済"),
	COMP_DELIVER(4, "発送完了"),
	CANCEL(9, "キャンセル");
	
	private Integer state;
	private String description;
	
	
	private StateEnum(Integer state, String description) {
		this.state = state;
		this.description = description;
	}


	public Integer getState() {
		return state;
	}


	public String getDescription() {
		return description;
	}
	
	
	public static List<StateEnum> getAllState() {
		return Arrays.asList(StateEnum.values());
	}
	
	
	
	public static StateEnum findState(Integer state) {
		
		for (StateEnum stateenum : StateEnum.values()) {
			if(stateenum.getState() == state) {
				return stateenum;
			}
		}
		
		return CANCEL;
	}
	
	
}

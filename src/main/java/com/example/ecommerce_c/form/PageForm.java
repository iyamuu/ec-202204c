package com.example.ecommerce_c.form;

/**
 * ページ範囲を指定するためのFormクラス.
 * 
 * @author daina.teranishi
 *
 */
public class PageForm {

	private Integer from;
	private Integer to;
	private String name;

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PageForm [from=" + from + ", to=" + to + ", name=" + name + "]";
	}
}

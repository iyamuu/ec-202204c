package com.example.ecommerce_c.domain;

/**
 * 決済処理を行うWEBAPIから、レスポンス情報を扱うドメイン.
 * 
 * @author hvthinh
 *
 */
public class OrderTransactionStatus {
	/** 決済状態 */
	private String status;
	/** 決済状態メッセージ */
	private String message;
	/** 決済状態コード */
	private String error_code;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	@Override
	public String toString() {
		return "OrderTransaction [status=" + status + ", message=" + message + ", errorCode=" + error_code + "]";
	}

}

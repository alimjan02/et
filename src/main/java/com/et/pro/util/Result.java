package com.et.pro.util;

public class Result {

	public Result() {

	}

	public Result(String status, String message, Object rows, long total) {
		setRows(rows);
		setMessage(message);
		setStatus(status);
		setTotal(total);
	}

	private String status;
	private Boolean result;
	private String message;
	private Object rows;
	private long total;

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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	
}

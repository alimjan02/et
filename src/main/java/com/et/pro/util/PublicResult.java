package com.et.pro.util;

public class PublicResult {

	public static Result SUCCESS(Object result) {
		return new Result("SUCCESS", "", result, 0);
	}

	public static Result SUCCESS(Object result, long total) {
		return new Result("SUCCESS", "", result, total);
	}

	public static Result SUCCESS(String message) {
		return new Result("SUCCESS", message, null, 0);
	}

	public static Result ERROR(String message) {
		return new Result("ERROR", message, null, 0);
	}
	
	public static Result AUTH(String message) {
		return new Result("AUTH", message, null, 0);
	}

}

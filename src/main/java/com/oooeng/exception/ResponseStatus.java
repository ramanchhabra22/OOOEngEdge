package com.oooeng.exception;

public enum ResponseStatus {

	OK(200, "OK"),
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	BAD_REQUEST(400, "Bad Request"),
	UNAUTHORIZED(403, "UNAUTHORIZED"),
	INVALID_REQUEST(1002, "Invalid Request,parameters are incorrect"),
	NOT_VALID_JSON(1012, "NOT_VALID_JSON");


	private final int value;

	private final String reasonPhrase;

	ResponseStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	@Override
	public String toString() {
		return Integer.toString(this.value);
	}
}

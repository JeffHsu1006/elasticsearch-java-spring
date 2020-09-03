package com.course.elasticsearchjavaspring.api.response;

public class SuccessResponse {
	private String goodResponse;

	public SuccessResponse() {

	}

	public SuccessResponse(String goodResponse) {
		super();
		this.goodResponse = goodResponse;
	}

	public String getGoodResponse() {
		return goodResponse;
	}

	public void setGoodResponse(String goodResponse) {
		this.goodResponse = goodResponse;
	}

}

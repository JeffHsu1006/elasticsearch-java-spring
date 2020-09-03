package com.course.elasticsearchjavaspring.exception;

public class IllegalApiParamException extends RuntimeException {

	private static final long serialVersionUID = -8696173600925135770L;

	public IllegalApiParamException(String s) {
		super(s);
	}
}

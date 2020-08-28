package com.course.elasticsearchjavaspring.api.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

	@RequestMapping(value = "/welcome")
	public String welcome() {
		System.out.println(StringUtils.join("Spring", "Success"));
		return "Hello, Everybodyyyyyy!";
	}
}

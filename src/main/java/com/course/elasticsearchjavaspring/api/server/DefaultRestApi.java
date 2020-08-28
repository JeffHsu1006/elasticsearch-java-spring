package com.course.elasticsearchjavaspring.api.server;

import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class DefaultRestApi {

	private Logger LOG = LoggerFactory.getLogger(DefaultRestApi.class);

	@RequestMapping(value = "/welcome")
	public String welcome() {
		LOG.info(StringUtils.join("Spring", "Success"));
		return "Hello, Everybodyyyyyy!";
	}

	@RequestMapping(value = "/time")
	public String time() {
		return LocalTime.now().toString();
	}
}

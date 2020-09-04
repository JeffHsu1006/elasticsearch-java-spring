package com.course.elasticsearchjavaspring.api.server;

import java.time.LocalTime;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "Default Rest API", description = "Documentation for Default Rest API")
public class DefaultRestApi {

	private Logger LOG = LoggerFactory.getLogger(DefaultRestApi.class);

	@RequestMapping(value = "/welcome")
	@Operation(summary = "Welcome", description = "Description for welcome API")
	public String welcome() {
		LOG.info(StringUtils.join("Spring", "Success"));
		return "Hello, Everybodyyyyyy!";
	}

	@RequestMapping(value = "/time")
	public String time() {
		return LocalTime.now().toString();
	}

	@GetMapping(value = "/header-one")
	public String headerByAnnotation(@RequestHeader(name = "User-agent") String headerUserAgent,
			@RequestHeader(name = "elasticsearchjavaspring", required = false) String headerPracticalJava) {
		return "User-Agent : " + headerUserAgent + ", Elasticsearch-Java-Spring : " + headerPracticalJava;
	}

	@GetMapping(value = "/header-two")
	public String headerByRequest(ServerHttpRequest request) {
		return "User-Agent : " + request.getHeaders().getValuesAsList("User-agent") + ", Elasticsearch-Java-Spring : "
				+ request.getHeaders().getValuesAsList("elasticsearchjavaspring");
	}

	@GetMapping("/random-error")
	public ResponseEntity<String> randomError() {
		int remainder = new Random().nextInt() % 5;
		var body = "Kibana";

		switch (remainder) {
		case 0:
			return ResponseEntity.ok().body(body);
		case 1:
		case 2:
			return ResponseEntity.badRequest().body(body);
		default:
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}
	}
}

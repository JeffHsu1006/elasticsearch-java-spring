package com.course.elasticsearchjavaspring.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.elasticsearchjavaspring.entity.Bag;
import com.course.elasticsearchjavaspring.service.BagService;

@RequestMapping(value = "/api/bag/v1")
@RestController
public class BagApi {

	@Autowired
	private BagService bagService;

	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public Bag random() {
		return bagService.generateBag();
	}
}

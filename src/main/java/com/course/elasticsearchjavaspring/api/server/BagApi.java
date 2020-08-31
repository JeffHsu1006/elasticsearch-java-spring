package com.course.elasticsearchjavaspring.api.server;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.elasticsearchjavaspring.entity.Bag;
import com.course.elasticsearchjavaspring.service.BagService;

import io.netty.util.internal.ThreadLocalRandom;

@RequestMapping(value = "/api/bag/v1")
@RestController
public class BagApi {

	private static final Logger LOG = LoggerFactory.getLogger(BagApi.class);

	@Autowired
	private BagService bagService;

	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public Bag random() {
		return bagService.generateBag();
	}

	@PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String echo(@RequestBody Bag bag) {
		LOG.info("Bag is {}", bag);
		return bag.toString();
	}

	@GetMapping(value = "/random-bags", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Bag> randomBags() {
		var result = new ArrayList<Bag>();

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 5); i++) {
			result.add(bagService.generateBag());
		}

		return result;
	}
}

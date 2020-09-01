package com.course.elasticsearchjavaspring.api.server;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.elasticsearchjavaspring.entity.Bag;
import com.course.elasticsearchjavaspring.repository.BagElasticRepository;
import com.course.elasticsearchjavaspring.service.BagService;

import io.netty.util.internal.ThreadLocalRandom;

@RequestMapping(value = "/api/bag/v1")
@RestController
public class BagApi {

	private static final Logger LOG = LoggerFactory.getLogger(BagApi.class);

	@Autowired
	private BagElasticRepository bagRepository;

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

	@GetMapping(value = "/count")
	public String countBag() {
		return "There are : " + bagRepository.count() + " bags";
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveBag(@RequestBody Bag bag) {
		var id = bagRepository.save(bag).getId();

		return "Saved with ID : " + id;
	}

	@GetMapping(value = "/{id}")
	public Bag getBag(@PathVariable("id") String bagId) {
		return bagRepository.findById(bagId).orElse(null);
	}

	@PutMapping(value = "/{id}")
	public String updateBag(@PathVariable("id") String bagId, @RequestBody Bag updatedBag) {
		updatedBag.setId(bagId);
		var newBag = bagRepository.save(updatedBag);

		return "Updated Bag with ID : " + newBag.getId();
	}

	@GetMapping(value = "/find-json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Bag> findBagsByBrandAndColor(@RequestBody Bag bag) {
		return bagRepository.findByBrandAndColor(bag.getBrand(), bag.getColor());
	}

	@GetMapping(value = "/bags/{brand}/{color}")
	public List<Bag> findBagsByPath(@PathVariable String brand, @PathVariable String color) {
		return bagRepository.findByBrandAndColor(brand, color);
	}

	@GetMapping(value = "/bags")
	public List<Bag> findBagsByParam(@RequestParam String brand, @RequestParam String color) {
		return bagRepository.findByBrandAndColor(brand, color);
	}
}

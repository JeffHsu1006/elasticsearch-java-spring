package com.course.elasticsearchjavaspring.api.server;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.elasticsearchjavaspring.api.response.ErrorResponse;
import com.course.elasticsearchjavaspring.entity.Bag;
import com.course.elasticsearchjavaspring.exception.IllegalApiParamException;
import com.course.elasticsearchjavaspring.repository.BagElasticRepository;
import com.course.elasticsearchjavaspring.service.BagService;

import io.netty.util.internal.ThreadLocalRandom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = "/api/bag/v1")
@RestController
@Tag(name = "Bag API", description = "Document for Bag API")
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

	@Operation(summary = "Echo car", description = "Echo given bag input")
	@PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String echo(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Bag to be echoed") @RequestBody Bag bag) {
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
	public List<Bag> findBagsByBrandAndColor(@RequestBody Bag bag, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		var pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "price"));
		return bagRepository.findByBrandAndColor(bag.getBrand(), bag.getColor(), pageable).getContent();
	}

	@GetMapping(value = "/bags/{brand}/{color}")
	@Operation(summary = "Find bags by path", description = "Find bags by path variable")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Everything is OK"),
			@ApiResponse(responseCode = "400", description = "Bad input parameter"), })
	public ResponseEntity<Object> findBagsByPath(
			@Parameter(description = "Brand to be found") @PathVariable String brand,
			@Parameter(description = "Color to be found", example = "Red") @PathVariable String color,
			@Parameter(description = "Page number (for pagination)") @RequestParam(defaultValue = "0") int page,
			@Parameter(description = "Number of items per page (for pagination)") @RequestParam(defaultValue = "10") int size) {
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.SERVER, "Spring");
		headers.add("Custom-Header", "Custom Response Header");

		// check whether parameter "color" is numeric
		if (StringUtils.isNumeric(color)) {
			var errorResponse = new ErrorResponse("Invalid color : " + color, LocalDateTime.now());
			// var successResponse = new SuccessResponse("SUCCESS!");

			return new ResponseEntity<Object>(errorResponse, headers, HttpStatus.BAD_REQUEST);
		}

		var pageable = PageRequest.of(page, size);
		var bags = bagRepository.findByBrandAndColor(brand, color, pageable).getContent();

		return ResponseEntity.ok().headers(headers).body(bags);
	}

	@GetMapping(value = "/bags")
	public List<Bag> findBagsByParam(@RequestParam String brand, @RequestParam String color,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		if (StringUtils.isNumeric(color)) {
			throw new IllegalArgumentException("Invalid color : " + color);
		}

		if (StringUtils.isNumeric(brand)) {
			throw new IllegalApiParamException("Invalid brand : " + brand);
		}

		var pageable = PageRequest.of(page, size);
		return bagRepository.findByBrandAndColor(brand, color, pageable).getContent();
	}

	@GetMapping(value = "/bags/date")
	public List<Bag> findBagsReleasedAfter(
			@RequestParam(name = "first_release_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstReleaseDate) {
		return bagRepository.findByFirstReleaseDateAfter(firstReleaseDate);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleInvalidColorException(IllegalArgumentException e) {
		var message = "Exception, " + e.getMessage();
		LOG.warn(message);

		var errorResponse = new ErrorResponse(message, LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}

package com.course.elasticsearchjavaspring.api.server;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.course.elasticsearchjavaspring.entity.Bag;
import com.course.elasticsearchjavaspring.service.BagService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BagApiTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testRandom() {
		for (int i = 0; i < 100; i++) {
			webTestClient.get().uri("/api/bag/v1/random").exchange().expectBody(Bag.class).value(bag -> {
				assertTrue(BagService.BRANDS.contains(bag.getBrand()));
				assertTrue(BagService.COLORS.contains(bag.getColor()));
			});
		}
	}

	// @Test
	void testEcho() {
		fail("Not yet implemented");
	}

	// @Test
	void testRandomBags() {
		fail("Not yet implemented");
	}

	// @Test
	void testCountBag() {
		fail("Not yet implemented");
	}

	@Autowired
	@Qualifier("randomBagService")
	private BagService bagService;

	@Test
	void testSaveBag() {
		var randomBag = bagService.generateBag();

		for (int i = 0; i < 100; i++) {
			assertTimeout(Duration.ofSeconds(1),
					() -> webTestClient.post().uri("/api/bag/v1").contentType(MediaType.APPLICATION_JSON)
							.bodyValue(randomBag).exchange().expectStatus().is2xxSuccessful());
		}
	}

	// @Test
	void testGetBag() {
		fail("Not yet implemented");
	}

	// @Test
	void testUpdateBag() {
		fail("Not yet implemented");
	}

	// @Test
	void testFindBagsByBrandAndColor() {
		fail("Not yet implemented");
	}

	// @Test
	void testFindBagsByPath() {
		fail("Not yet implemented");
	}

	@Test
	void testFindBagsByParam() {
		final int size = 5;

		for (var brand : BagService.BRANDS) {
			for (var color : BagService.COLORS) {
				webTestClient.get()
						.uri(uriBuilder -> uriBuilder.path("/api/bag/v1/bags").queryParam("brand", brand)
								.queryParam("color", color).queryParam("page", 0).queryParam("size", size).build())
						.exchange().expectBodyList(Bag.class).value(bags -> {
							assertNotNull(bags);
							assertTrue(bags.size() <= size);
						});
			}
		}
	}

	// @Test
	void testFindBagsReleasedAfter() {
		fail("Not yet implemented");
	}

	// @Test
	void testHandleInvalidColorException() {
		fail("Not yet implemented");
	}

}

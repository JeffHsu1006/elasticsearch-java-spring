package com.course.elasticsearchjavaspring.common;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.course.elasticsearchjavaspring.entity.Bag;
import com.course.elasticsearchjavaspring.repository.BagElasticRepository;
import com.course.elasticsearchjavaspring.service.BagService;

@Component
public class BagElasticDatasource {

	private static final Logger LOG = LoggerFactory.getLogger(BagElasticDatasource.class);

	@Autowired
	private BagElasticRepository bagRepository;

	@Autowired
	@Qualifier("randomBagService")
	private BagService bagService;

	@Autowired
	@Qualifier("webClientElasticsearch")
	private WebClient webClient;

	@EventListener(ApplicationReadyEvent.class)
	public void populateData() {
		var response = webClient.delete().uri("/elasticsearch-java-spring").retrieve().bodyToMono(String.class).block();
		LOG.info("End delete with response {}", response);

		var bags = new ArrayList<Bag>();

		for (int i = 0; i < 10_000; i++) {
			bags.add(bagService.generateBag());
		}

		bagRepository.saveAll(bags);

		LOG.info("Saved {} bags", bagRepository.count());
	}
}

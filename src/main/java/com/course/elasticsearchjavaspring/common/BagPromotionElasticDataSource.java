package com.course.elasticsearchjavaspring.common;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.course.elasticsearchjavaspring.entity.BagPromotion;
import com.course.elasticsearchjavaspring.repository.BagPromotionElasticRepository;

@Component
public class BagPromotionElasticDataSource {

	@Autowired
	private BagPromotionElasticRepository bagPromotionElasticRepository;

	private static final Logger LOG = LoggerFactory.getLogger(BagPromotionElasticDataSource.class);

	@EventListener(ApplicationReadyEvent.class)
	public void populateData() {
		bagPromotionElasticRepository.deleteAll();

		var bagPromotions = new ArrayList<BagPromotion>();

		var promotion1 = new BagPromotion();
		promotion1.setType("bonus");
		promotion1.setDescription("Purchase in cash and get free luxury dinner");

		var promotion2 = new BagPromotion();
		promotion2.setType("bonus");
		promotion2.setDescription("Purchase luxury bag and get free trip to Taiwan");

		var promotion3 = new BagPromotion();
		promotion3.setType("bonus");
		promotion3.setDescription("Purchase two bags and get 20 gram of gold");

		var promotion4 = new BagPromotion();
		promotion4.setType("discount");
		promotion4.setDescription("Purchase in cash and 1% discount");

		var promotion5 = new BagPromotion();
		promotion5.setType("discount");
		promotion5.setDescription("Purchase before end of month to get 1.5% discount");

		var promotion6 = new BagPromotion();
		promotion6.setType("discount");
		promotion6.setDescription("Today only! We gives 0.5% additional discount");

		bagPromotions.add(promotion1);
		bagPromotions.add(promotion2);
		bagPromotions.add(promotion3);
		bagPromotions.add(promotion4);
		bagPromotions.add(promotion5);
		bagPromotions.add(promotion6);

		bagPromotionElasticRepository.saveAll(bagPromotions);

		LOG.info("Saved dummy promotion data : {}", bagPromotionElasticRepository.count());
	}
}

package com.course.elasticsearchjavaspring.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultBagPromotionService implements BagPromotionService {

	@Override
	public boolean isValidPromotionType(String promotionType) {
		return PROMOTION_TYPES.contains(promotionType.toLowerCase());
	}

}

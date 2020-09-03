package com.course.elasticsearchjavaspring.service;

import java.util.List;

public interface BagPromotionService {

	List<String> PROMOTION_TYPES = List.of("bonus", "discount");

	boolean isValidPromotionType(String promotionType);
}

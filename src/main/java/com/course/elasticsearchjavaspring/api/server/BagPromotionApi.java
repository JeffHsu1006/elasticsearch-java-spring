package com.course.elasticsearchjavaspring.api.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.elasticsearchjavaspring.entity.BagPromotion;
import com.course.elasticsearchjavaspring.exception.IllegalApiParamException;
import com.course.elasticsearchjavaspring.repository.BagPromotionElasticRepository;
import com.course.elasticsearchjavaspring.service.BagPromotionService;

@RestController
@RequestMapping(value = "/api/bag/v1")
public class BagPromotionApi {

	@Autowired
	private BagPromotionService bagPromotionService;

	@Autowired
	private BagPromotionElasticRepository bagPromotionElasticRepository;

	@GetMapping(value = "/promotions")
	public List<BagPromotion> listAvailablePromotions(@RequestParam(name = "type") String promotionType,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		if (!bagPromotionService.isValidPromotionType(promotionType)) {
			throw new IllegalApiParamException("Invalid promotion type : " + promotionType);
		}

		var pageable = PageRequest.of(page, size);
		return bagPromotionElasticRepository.findByType(promotionType, pageable).getContent();
	}
}

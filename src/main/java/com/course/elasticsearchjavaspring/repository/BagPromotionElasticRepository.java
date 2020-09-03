package com.course.elasticsearchjavaspring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.course.elasticsearchjavaspring.entity.BagPromotion;

@Repository
public interface BagPromotionElasticRepository extends ElasticsearchRepository<BagPromotion, String> {

	public Page<BagPromotion> findByType(String type, Pageable pageable);
}

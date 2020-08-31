package com.course.elasticsearchjavaspring.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.course.elasticsearchjavaspring.entity.Bag;

@Repository
public interface BagElasticRepository extends ElasticsearchRepository<Bag, String> {

}

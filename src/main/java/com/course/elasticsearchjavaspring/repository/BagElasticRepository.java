package com.course.elasticsearchjavaspring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.course.elasticsearchjavaspring.entity.Bag;

@Repository
public interface BagElasticRepository extends ElasticsearchRepository<Bag, String> {

	public Page<Bag> findByBrandAndColor(String band, String color, Pageable pageable);

	public List<Bag> findByFirstReleaseDateAfter(LocalDate date);
}

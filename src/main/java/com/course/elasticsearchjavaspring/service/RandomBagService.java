package com.course.elasticsearchjavaspring.service;

import org.springframework.stereotype.Service;

import com.course.elasticsearchjavaspring.entity.Bag;

import io.netty.util.internal.ThreadLocalRandom;

@Service
public class RandomBagService implements BagService {

	@Override
	public Bag generateBag() {
		var brand = BRANDS.get(ThreadLocalRandom.current().nextInt(0, BRANDS.size()));
		var color = COLORS.get(ThreadLocalRandom.current().nextInt(0, COLORS.size()));
		var type = TYPES.get(ThreadLocalRandom.current().nextInt(0, TYPES.size()));

		return new Bag(brand, color, type);
	}

}

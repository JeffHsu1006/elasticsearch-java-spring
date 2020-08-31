package com.course.elasticsearchjavaspring.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.course.elasticsearchjavaspring.entity.Bag;
import com.course.elasticsearchjavaspring.util.RandomDateUtil;

import io.netty.util.internal.ThreadLocalRandom;

@Service
public class RandomBagService implements BagService {

	@Override
	public Bag generateBag() {
		var brand = BRANDS.get(ThreadLocalRandom.current().nextInt(0, BRANDS.size()));
		var color = COLORS.get(ThreadLocalRandom.current().nextInt(0, COLORS.size()));
		var type = TYPES.get(ThreadLocalRandom.current().nextInt(0, TYPES.size()));
		var available = ThreadLocalRandom.current().nextBoolean();
		var price = ThreadLocalRandom.current().nextInt(5000, 12001);
		var firstReleaseDate = RandomDateUtil.gerenateRandomLocalDate();

		var additionalFeatures = new ArrayList<String>();

		for (int i = 0; i < ADDITIONAL_FEATURES.size(); i++) {
			int randomCount = ThreadLocalRandom.current().nextInt(ADDITIONAL_FEATURES.size());
			additionalFeatures.add(ADDITIONAL_FEATURES.get(i));
			i += randomCount;
		}

		var result = new Bag(brand, color, type, price, available, firstReleaseDate);
		result.setAdditionFeatures(additionalFeatures);

		return result;
	}

}

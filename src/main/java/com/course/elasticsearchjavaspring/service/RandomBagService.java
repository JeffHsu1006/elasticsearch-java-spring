package com.course.elasticsearchjavaspring.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.course.elasticsearchjavaspring.entity.Bag;
import com.course.elasticsearchjavaspring.entity.Material;
import com.course.elasticsearchjavaspring.entity.Zipper;
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

		var material_type = MATERIAL_TYPES.get(ThreadLocalRandom.current().nextInt(MATERIAL_TYPES.size()));
		var thickness = ThreadLocalRandom.current().nextInt(1, 10);

		var material = new Material();
		material.setType(material_type);
		material.setThickness(thickness);

		var zippers = new ArrayList<Zipper>();
		for (int i = 0; i < 3; i++) {
			var zipper = new Zipper();
			var manufacturer = ZIPPER_MANUFACTURES.get(ThreadLocalRandom.current().nextInt(ZIPPER_MANUFACTURES.size()));
			var size = ThreadLocalRandom.current().nextInt(1, 5);
			var zipperPrice = ThreadLocalRandom.current().nextInt(10, 60);

			zipper.setManufacturer(manufacturer);
			zipper.setSize(size);
			zipper.setPrice(zipperPrice);

			zippers.add(zipper);
		}

		var result = new Bag(brand, color, type, price, available, firstReleaseDate);
		result.setAdditionFeatures(additionalFeatures);
		result.setMaterial(material);
		result.setZippers(zippers);

		return result;
	}

}

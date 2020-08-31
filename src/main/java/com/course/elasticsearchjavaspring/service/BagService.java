package com.course.elasticsearchjavaspring.service;

import java.util.List;

import com.course.elasticsearchjavaspring.entity.Bag;

public interface BagService {
	List<String> BRANDS = List.of("PLAYBOY", "FOSSIL", "ALPAKA", "CALVIN kLEIN", "CHANEL");

	List<String> COLORS = List.of("Gray", "Black", "Red", "Green", "Yellow");

	List<String> TYPES = List.of("Wallet", "Pouch", "Backpack", "HangBags", "Knapsack");

	List<String> ADDITIONAL_FEATURES = List.of("Zipper", "Pocket", "Buckle", "GPS", "Audio");

	List<String> MATERIAL_TYPES = List.of("Glass", "Plasic", "Leather");

	List<String> ZIPPER_MANUFACTURES = List.of("YKK", "KS", "SBS");

	Bag generateBag();
}

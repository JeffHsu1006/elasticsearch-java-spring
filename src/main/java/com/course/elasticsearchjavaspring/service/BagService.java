package com.course.elasticsearchjavaspring.service;

import java.util.List;

import com.course.elasticsearchjavaspring.entity.Bag;

public interface BagService {
	List<String> BRANDS = List.of("PLAYBOY", "FOSSIL", "ALPAKA");

	List<String> COLORS = List.of("Gray", "Black", "Red");

	List<String> TYPES = List.of("Wallet", "Pouch", "Backpack");

	List<String> ADDITIONAL_FEATURES = List.of("Zipper", "Pocket", "Buckle", "GPS", "Audio");

	Bag generateBag();
}

package com.course.elasticsearchjavaspring.util;

import java.time.LocalDate;
import java.time.Month;

import io.netty.util.internal.ThreadLocalRandom;

public class RandomDateUtil {

	private static final long minDay = LocalDate.of(2000, Month.JANUARY, 1).toEpochDay();

	private static final long maxDay = LocalDate.now().toEpochDay();

	public static LocalDate gerenateRandomLocalDate() {
		long randomDay = minDay + ThreadLocalRandom.current().nextLong(maxDay - minDay);
		return LocalDate.ofEpochDay(randomDay);

	}
}

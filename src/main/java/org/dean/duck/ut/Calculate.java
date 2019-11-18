package org.dean.duck.ut;

import java.util.Optional;

public class Calculate {
	public Integer add(Integer a, Integer b) {
		return Optional.of(a).orElse(0) + Optional.of(b).orElse(0);
	}

	public Integer substract(Integer a, Integer b) {
		return Optional.of(a).orElse(0) - Optional.of(b).orElse(0);
	}
}

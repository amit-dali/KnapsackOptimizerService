package com.msk.object.model.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author amit dali
 *
 */
public class ObjectBuilder<T> {
	private final Supplier<T> instantiator;
	private List<Consumer<T>> instanceModifiers = new ArrayList<>();

	public ObjectBuilder(Supplier<T> instantiator) {
		this.instantiator = instantiator;
	}

	public static <T> ObjectBuilder<T> of(Supplier<T> instantiator) {
		return new ObjectBuilder<T>(instantiator);
	}

	public <U> ObjectBuilder<T> with(BiConsumer<T, U> consumer, U value) {
		Consumer<T> c = instance -> consumer.accept(instance, value);
		instanceModifiers.add(c);
		return this;
	}

	public T build() {
		T value = instantiator.get();
		instanceModifiers.forEach(modifier -> modifier.accept(value));
		instanceModifiers.clear();
		return value;
	}

}

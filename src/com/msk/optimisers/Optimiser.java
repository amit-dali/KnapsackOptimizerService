package com.msk.optimisers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msk.object.model.Item;
import com.msk.object.model.Solution;

/**
 * enables one to upload a custom optimiser implementing an API you specify.
 * 
 * @author amit dali
 */
public abstract class Optimiser {

	private static final Logger LOGGER = LogManager.getLogger(Optimiser.class);
	protected List<Item> items;
	protected int capacity;

	protected Optimiser(List<Item> items, int capacity) {
		LOGGER.info("Optimiser", "Init");
		this.items = items;
		this.capacity = capacity;
		LOGGER.debug("Optimiser", "items=" + this.items);
		LOGGER.debug("Optimiser", "capacity=" + this.capacity);
	}

	public abstract Solution solve();

	public double getWeight(List<Item> items) {
		LOGGER.info("getWeight", "Enter");
		double weight = 0;
		for (Item item : items) {
			weight += item.getWeight();
		}
		LOGGER.debug("Optimiser", "weight=" + weight);
		LOGGER.info("getWeight", "Exit");
		return weight;
	}

	public double getValue(List<Item> items) {
		LOGGER.info("getValue", "Enter");
		double value = 0;
		for (Item item : items) {
			value += item.getValue();
		}
		LOGGER.debug("getValue", "value=" + value);
		LOGGER.info("getValue", "Exit");
		return value;
	}
}

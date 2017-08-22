package com.msk.object.model.helpers;

import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.msk.object.model.Item;

public enum ItemSorter {
	INSTANCE;
	private static final Logger LOGGER = LogManager.getLogger(ItemSorter.class);

	public Comparator<Item> byLabel() {
		LOGGER.info("byLabel", "enter");
		return new Comparator<Item>() {
			public int compare(Item i1, Item i2) {
				LOGGER.debug("byLabel", "Item one:" + i1 + "Item two:" + i2);
				LOGGER.info("byLabel", "exit");
				return i1.getLabel() - i2.getLabel();
			}
		};
	}

	public Comparator<Item> byRatio() {
		LOGGER.info("byRatio", "enter");
		return new Comparator<Item>() {
			public int compare(Item i1, Item i2) {
				LOGGER.debug("byRatio", "Item one:" + i1 + "Item two:" + i2);
				LOGGER.info("byLabel", "exit");
				return Double.compare(i2.getRatio(), i1.getRatio());
			}
		};
	}

}

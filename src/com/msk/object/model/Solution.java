package com.msk.object.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.msk.object.model.helpers.ItemSorter;

/**
 * @author amit dali
 *
 */
public class Solution implements Serializable {

	private static final long serialVersionUID = 1L;
	private String approach;
	private List<Item> items;
	private double weight;
	private double value;

	public String getApproach() {
		return approach;
	}

	public void setApproach(String approach) {
		this.approach = approach;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(approach);
		builder.append(": ");
		builder.append(value);
		builder.append(" ");
		builder.append(weight);
		builder.append("\n");
		Collections.sort(items, ItemSorter.INSTANCE.byLabel());
		for (Item item : items) {
			builder.append(item.getLabel());
			builder.append(" ");
		}
		return builder.toString();
	}
}

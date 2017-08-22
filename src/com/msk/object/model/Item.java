package com.msk.object.model;

import java.io.Serializable;

/**
 * @author amit dali
 *
 */
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	private int label;
	private double value, weight;

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getRatio() {
		return value / weight;
	}

	@Override
	public String toString() {
		return "Item [label=" + label + ", value=" + value + ", weight=" + weight + "]";
	}
}

package com.msk.optimisers;

import java.util.LinkedList;
import java.util.List;

import com.msk.object.model.Item;
import com.msk.object.model.Solution;
import com.msk.object.model.builder.ObjectBuilder;

/**
 * @author amit dali
 *
 */
public class BruteForceOptimiser extends Optimiser {

	public BruteForceOptimiser(List<Item> items, int capacity) {
		super(items, capacity);
	}

	@Override
	public Solution solve() {
		Solution best = ObjectBuilder.of(Solution::new).with(Solution::setItems, new LinkedList<>()).build();
		for (List<Item> subset : subsets(items)) {
			double weight = getWeight(subset);
			if (weight <= capacity) {
				double value = getValue(subset);
				if (value > best.getValue()) {
					best.setValue(value);
					best.setWeight(weight);
					best.setItems(subset);
				}
			}
		}
		best.setApproach("Using Brute force the best feasible solution found");
		return best;
	}

	private List<List<Item>> subsets(List<Item> items) {
		List<List<Item>> subsets = new LinkedList<>();
		if (items.isEmpty()) {
			subsets.add(new LinkedList<>());
			return subsets;
		}
		Item first = items.get(0);
		List<List<Item>> subsubsets = subsets(items.subList(1, items.size()));
		for (List<Item> subset : subsubsets) {
			subsets.add(subset);
			List<Item> augmented = new LinkedList<>(subset);
			augmented.add(0, first);
			subsets.add(augmented);
		}
		return subsets;
	}
}

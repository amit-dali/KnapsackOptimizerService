package com.msk.optimisers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.msk.object.model.Item;
import com.msk.object.model.Solution;
import com.msk.object.model.builder.ObjectBuilder;
import com.msk.object.model.helpers.ItemSorter;

/**
 * @author amit dali
 *
 */
public class GreedyOptimiser extends Optimiser {

	public GreedyOptimiser(List<Item> items, int capacity) {
		super(items, capacity);
	}

	@Override
	public Solution solve() {
		Solution greedy = ObjectBuilder.of(Solution::new).with(Solution::setItems, new ArrayList<>(items)).build();
		Collections.sort(greedy.getItems(), ItemSorter.INSTANCE.byRatio());
		double capUsed = 0, value = 0;
		int i;
		for (i = 0; i < greedy.getItems().size(); i++) {
			Item item = greedy.getItems().get(i);
			if (capUsed + item.getWeight() > capacity)
				break;
			capUsed += item.getWeight();
			value += item.getValue();
		}
		greedy.setItems(greedy.getItems().subList(0, i));
		greedy.setWeight(capUsed);
		greedy.setValue(value);
		greedy.setApproach("Greedy solution (not necessarily optimal)");
		return greedy;
	}
}

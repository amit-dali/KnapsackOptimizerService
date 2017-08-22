package com.msk.optimisers;

import java.util.ArrayList;
import java.util.List;

import com.msk.object.model.Item;
import com.msk.object.model.Solution;
import com.msk.object.model.builder.ObjectBuilder;

/**
 * @author amit dali
 *
 */
public class DynamicProgrammingOptimiser extends Optimiser {

	private double[][] table;

	public DynamicProgrammingOptimiser(List<Item> items, int capacity) {
		super(items, capacity);
	}

	@Override
	public Solution solve() {
		table = new double[capacity + 1][items.size()];
		for (int j = 0; j < capacity + 1; j++)
			for (int i = 0; i < items.size(); i++)
				table[j][i] = -1;
		getCell(capacity, items.size() - 1);
		Solution best = traceTable();
		best.setApproach("Dynamic Programming solution");
		return best;
	}

	private Solution traceTable() {
		Solution best = ObjectBuilder.of(Solution::new).with(Solution::setItems, new ArrayList<>()).build();
		int i = items.size() - 1, j = capacity;
		while (i >= 0) {
			Item item = items.get(i);
			double without = i == 0 ? 0 : table[j][i - 1];
			if (table[j][i] != without) {
				best.getItems().add(item);
				best.setValue(best.getValue() + item.getValue());
				best.setWeight(best.getWeight() + item.getWeight());
				j -= (int) item.getWeight();
			}
			i--;
		}
		return best;
	}

	private double getCell(int j, int i) {
		if (i < 0 || j < 0)
			return 0;
		Item item = items.get(i);
		double with, without, cell = table[j][i];
		if (cell == -1) {
			if (item.getWeight() > j)
				with = -1;
			else
				with = item.getValue() + getCell(j - (int) item.getWeight(), i - 1);
			without = getCell(j, i - 1);
			cell = Math.max(with, without);
			table[j][i] = cell;
		}
		return cell;
	}
}

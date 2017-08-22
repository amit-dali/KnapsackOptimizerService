package com.msk.optimise.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.msk.object.model.Item;
import com.msk.object.model.builder.ObjectBuilder;
import com.msk.optimisers.BranchAndBoundOptimiser;
import com.msk.optimisers.BruteForceOptimiser;
import com.msk.optimisers.DynamicProgrammingOptimiser;
import com.msk.optimisers.GreedyOptimiser;
import com.msk.optimisers.Optimiser;

public class StartUp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		List<Item> items = new LinkedList<>();
		for (int i = 0; i < count; i++) {
			Item item = ObjectBuilder.of(Item::new).with(Item::setLabel, scanner.nextInt())
					.with(Item::setValue, scanner.nextDouble()).with(Item::setWeight, scanner.nextDouble()).build();
			items.add(item);
		}
		int capacity = scanner.nextInt();
		List<Optimiser> solvers = new ArrayList<>();
		if (items.size() <= 20)
			solvers.add(new BruteForceOptimiser(items, capacity));
		solvers.add(new GreedyOptimiser(items, capacity));
		solvers.add(new DynamicProgrammingOptimiser(items, capacity));
		solvers.add(new BranchAndBoundOptimiser(items, capacity));

		for (Optimiser solver : solvers) {
			System.out.println(solver.solve());
		}

		if (scanner != null) {
			scanner.close();
		}

	}
}

package com.msk.optimisers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.msk.object.model.Item;
import com.msk.object.model.Solution;
import com.msk.object.model.builder.ObjectBuilder;
import com.msk.object.model.helpers.ItemSorter;

/**
 * @author amit dali
 *
 */
public class BranchAndBoundOptimiser extends Optimiser {

	private class Node implements Comparable<Node> {

		public int h;
		List<Item> taken;
		public double bound, value, weight;

		public Node() {
			taken = new ArrayList<>();
		}

		public Node(Node parent) {
			h = parent.h + 1;
			taken = new ArrayList<>(parent.taken);
			bound = parent.bound;
			value = parent.value;
			weight = parent.weight;
		}

		public int compareTo(Node other) {
			return (int) (other.bound - bound);
		}

		public void computeBound() {
			int i = h;
			double w = weight;
			bound = value;
			Item item;
			do {
				item = items.get(i);
				if (w + item.getWeight() > capacity)
					break;
				w += item.getWeight();
				bound += item.getValue();
				i++;
			} while (i < items.size());
			bound += (capacity - w) * (item.getValue() / item.getWeight());
		}
	}

	public BranchAndBoundOptimiser(List<Item> items, int capacity) {
		super(items, capacity);
	}

	@Override
	public Solution solve() {
		Collections.sort(items, ItemSorter.INSTANCE.byRatio());
		Node best = new Node();
		Node root = new Node();
		root.computeBound();
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(root);
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.bound > best.value && node.h < items.size() - 1) {
				Node with = new Node(node);
				Item item = items.get(node.h);
				with.weight += item.getWeight();
				if (with.weight <= capacity) {
					with.taken.add(items.get(node.h));
					with.value += item.getValue();
					with.computeBound();
					if (with.value > best.value) {
						best = with;
					}
					if (with.bound > best.value) {
						q.offer(with);
					}
				}
				Node without = new Node(node);
				without.computeBound();
				if (without.bound > best.value) {
					q.offer(without);
				}
			}
		}
		return ObjectBuilder.of(Solution::new).with(Solution::setValue, best.value)
				.with(Solution::setWeight, best.weight).with(Solution::setItems, best.taken)
				.with(Solution::setApproach, "Using Branch and Bound the best feasible solution found").build();
	}
}

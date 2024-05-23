package com.github.mauricioaniche.ck.metric;


import java.util.Stack;

public class NestedBlock {
	private int current = 0;
	private int max = 0;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getMax() {
		return max;
	}

	public void plusOne() {
		current++;
		max = Math.max(current, max);
	}

	public void popBlock(Stack<Boolean> thisNodes) {
		Boolean pop = thisNodes.pop();
		if (pop)
			current--;
	}
}
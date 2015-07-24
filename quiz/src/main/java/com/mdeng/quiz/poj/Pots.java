package com.mdeng.quiz.poj;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Pots {
	private final String[] steps = new String[] { "FILL(1)", "DROP(1)",
			"POUR(1,2)", "FILL(2)", "DROP(2)", "POUR(2,1)" };
	int maxA, maxB;

	boolean[][] flags1 = new boolean[101][101];
	int[][] costs1 = new int[101][101];
	
	class Pair {
		int a;
		int b;

		Pair() {
		}

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	class Node {
		Pair pair;
		Node[] children = new Node[6];
		Node parent;
		int stepIndex;

		public Node() {
		}

		public Node(Pair pair, int index) {
			this.pair = pair;
			this.stepIndex = index;
		}
	}

	void find(int a, int b, int c) {

		Node root = new Node(new Pair(), 0);
		Queue<Node> queue = new LinkedList<Node>();

		queue.add(root);
		flags1[0][0] = true;

		while (!queue.isEmpty()) {
			Node node = queue.remove();
			int currentLayer = getCost(node.pair);
			if (node.pair.a == c || node.pair.b == c) {
				print(currentLayer, node);
				return;
			}

			int childrenIndex = 0;
			for (int i = 1; i < 7; i++) {
				if (isValid(node.pair, i)) {
					Pair nextPair = nextPair(node.pair, i);
					if (!hasVisited(nextPair)) {
						Node nextNode = new Node(nextPair, i);
						nextNode.parent = node;
						node.children[childrenIndex++] = nextNode;
						queue.add(nextNode);

						flags1[nextPair.a][nextPair.b] = true;
						costs1[nextPair.a][nextPair.b] = costs1[node.pair.a][node.pair.b]+1;
					}
				}
			}
		}
		System.out.println("impossible");
	}

	int getCost(Pair pair)
	{
		return costs1[pair.a][pair.b];
	}
	
	boolean hasVisited(Pair pair)
	{
		return flags1[pair.a][pair.b];
	}
	
	private void print(int currentLayer, Node node) {
		if (node.parent == null) {
			System.out.printf("%d\n", currentLayer);
			return;
		}
		print(currentLayer, node.parent);
		System.out.println(steps[node.stepIndex - 1]);
	}

	Pair nextPair(Pair pair, int i) {
		if (i == 1)
			return new Pair(maxA, pair.b);
		if (i == 2)
			return new Pair(0, pair.b);
		if (i == 3) {
			if (pair.a + pair.b <= maxB)
				return new Pair(0, pair.a + pair.b);
			else
				return new Pair(pair.a - (maxB - pair.b), maxB);
		}

		if (i == 4)
			return new Pair(pair.a, maxB);
		if (i == 5)
			return new Pair(pair.a, 0);
		if (i == 6) {
			if (pair.a + pair.b <= maxA)
				return new Pair(pair.a + pair.b, 0);
			else
				return new Pair(maxA, pair.a + pair.b - maxA);
		}
		return null;
	}

	boolean isValid(Pair pair, int i) {
		if (i == 1)
			return pair.a < maxA;
		if (i == 2)
			return pair.a > 0;
		if (i == 3)
			return pair.a > 0 && pair.b < maxB;
		if (i == 4)
			return pair.b < maxB;
		if (i == 5)
			return pair.b > 0;
		if (i == 6)
			return pair.b > 0 && pair.a < maxA;
		return false;
	}

	public static void main(String[] args) {
		/*Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();*/

		for (int i = 1; i <101 ; i++) {
			for (int j = 1; j < 101; j++) {
				for (int j2 = 1; j2 < 101; j2++) {
					if (j2<=Math.max(j, j))
					{
						System.out.println("--"+i+""+j+""+j2+"--");
						Pots pots = new Pots();
						pots.maxA = i;
						pots.maxB = j;
						pots.find(i, j, j2);
					}	
				}
			}
		}
		/*Pots pots = new Pots();
		pots.maxA = a;
		pots.maxB = b;
		pots.find(a, b, c);*/
	}
}
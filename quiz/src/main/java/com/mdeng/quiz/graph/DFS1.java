package com.mdeng.quiz.graph;

import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

public class DFS1 {

	class Point 
	{
		public Point(int a, int b) {
		x=a;y=b;
		}
		int x;
		int y;
		void up() {y++;}
		void down() {y--;}
		void left() {x--;}
		void right() {x++;}
		boolean isValid() { return x<=3 && y<=3;}
		boolean reachEnd() { return x==3 && y==3;}
		@Override
		public String toString()
		{
			return "(" + x + ", " + y + ") ";
		}
		
	}
	
	static List<Point> queue = new LinkedList<>();
	Point[][] table = new Point[3][3];
	
	void init()
	{
		Point p = new Point(1, 1);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				table[i][j] = new Point(i+1, j+1);
			}
		}
	}
	
	void dfs(int x, int y)
	{
		if (x>=3 || y>=3) return;
		if (x==2&&y==2)
		{
			printPath(queue);
			return;
		}
		
		Point cur = new Point(x, y+1);
		queue.add(cur);
		dfs(x, y+1);
		queue.remove(cur);
		
		cur = new Point(x+1, y);
		queue.add(cur);
		dfs(x+1, y);
		queue.remove(cur);
	}

	private void printPath(List<Point> queue2) {
		for (Point point : queue2) {
			System.out.print(point.toString());
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		DFS1 dfs1 = new DFS1();
		queue.add(dfs1.new Point(0, 0));
		dfs1.dfs(0, 0);
	}
}

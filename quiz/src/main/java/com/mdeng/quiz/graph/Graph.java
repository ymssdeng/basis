package com.mdeng.quiz.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {

	List <Vertex> vlst;
	class Vertex
	{
		int value;
		int color;
		int cost;
		Vertex pV;
		List<Edge> neighbours;
		
		public Vertex nextNonVisited() {
			for (Edge edge : neighbours) {
				if (edge.v.color == 0)
					return edge.v;
			}
			return null;
		}
	}
	
	class Edge
	{
		int weight;
		Vertex v;
	}
	
	void bfs(List<Vertex> g, Vertex src)
	{
		Queue<Vertex> queue = new LinkedList<>();
		src.color = 1;
		queue.add(src);
		while (!queue.isEmpty()) {
			Vertex tVertex = queue.remove();
			for (Edge e : tVertex.neighbours) {
				if (e.v.color == 0)
				{
					e.v.color = 1;
					e.v.pV = tVertex;
					e.v.cost = tVertex.cost+1;
					queue.add(e.v);
				}
			}
			tVertex.color = 2;
		}
		
	}
	
	void printPath(Vertex s, Vertex t)
	{
		if (s==t)
		{
			visit(s);
			return;
		}
		if (t.pV == null)
			return;
		else {
			printPath(s, t.pV);
			visit(t);
		}
	}
	
	void visit(Vertex v)
	{
		
	}
	
	void dfs(List<Vertex> g)
	{
		for (Vertex vertex : g) {
			if (vertex.color == 0)
				dfs_visit(vertex);
		}
	}
	
	void dfs_visit(Vertex v)
	{
		v.color = 1;
		for (Edge e : v.neighbours) {
			if (e.v.color == 0)
			{
				e.v.pV = v;
				dfs_visit(e.v);
			}
		}
		v.color = 2;
	}
	
	void dfs_visit2(Vertex v)
	{
		Stack<Vertex> stack = new Stack<>();
		v.color = 1;
		stack.push(v);
		
		while (!stack.isEmpty()) {
			Vertex t = stack.peek();
			Vertex t2 = t.nextNonVisited();
			if (t2 != null)
			{
				t2.color = 1;
				t2.pV = t;
				stack.push(t2);
			}
			else {
				Vertex tmpVertex = stack.pop();
				tmpVertex.color = 2;
			}
		}
	}
}

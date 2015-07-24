package com.mdeng.quiz.graph;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim {

	class Vertex implements Comparable<Vertex>
	{
		List<Edge> neighbours;
		int key;
		int id;
		Vertex pV;
		@Override
		public int compareTo(Vertex o) {
			
			return key<o.key?-1:1;
		}
	}
	
	class Edge
	{
		Vertex v;
		int weight;
	}
	
	void prim(List<Vertex> g, Vertex src)
	{
		// init
		for (Vertex vertex : g) {
			vertex.key = Integer.MAX_VALUE;
		}
		src.key = 0;
		
		//heap
		Queue<Vertex> queue = new PriorityQueue<Vertex>();
		for (Vertex vertex : g) {
			queue.add(vertex);
		}
		
		while (!queue.isEmpty()) {
			Vertex t = queue.remove();
			for (Edge e : t.neighbours) {
				if (queue.contains(e.v) && e.weight<e.v.key)
				{
					e.v.pV = t;
					e.v.key = e.weight;
					// do not need to maintain the heap?
				}
			}
			
		}
	}
}

package com.mdeng.quiz.graph;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	class Vertex implements Comparable<Vertex>
	{
		List<Edge> neighbours;
		int distance;
		Vertex pV;
		@Override
		public int compareTo(Vertex o) {
			return distance>o.distance?1:-1;
		}
	}
	class Edge
	{
		int weight;
		Vertex v;
	}
	
	void dijkstra(List<Vertex> g, Vertex src)
	{
		//init
		for (Vertex vertex : g) {
			vertex.distance = Integer.MAX_VALUE;
		}
		src.distance = 0;
		
		//heap
		Queue<Vertex> queue = new PriorityQueue<>();
		for (Vertex vertex : g) {
			queue.add(vertex);
		}
		
		//dijkstra
		while (!queue.isEmpty()) {
			Vertex v = queue.remove();
			for (Edge e : v.neighbours) {
				if (e.v.distance>v.distance+e.weight)
				{
					e.v.distance = v.distance+e.weight;
					e.v.pV = v;
				}
			}
		}
	}
}
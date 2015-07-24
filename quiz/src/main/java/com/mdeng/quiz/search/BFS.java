package com.mdeng.quiz.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS
{
	class Vertex
	{
		List <Edge> neighbors;
		int value;
		int color; // 0--white, 1--grey, 2--black
		int cost;
		Vertex pV;
	}

	class Edge
	{
		int weight;
		Vertex v;
	}

	void bfs( List <Vertex> g, Vertex src )
	{
		Queue <Vertex> queue = new LinkedList <>();
		src.color = 1;
		queue.add(src);
		while (!queue.isEmpty())
		{
			Vertex vertex = queue.remove();
			for (Edge e : vertex.neighbors)
			{
				if (e.v.color == 0)
				{
					e.v.color = 1;
					e.v.cost = vertex.cost + 1;
					e.v.pV = vertex;
					queue.add(e.v);
				}
			}
			vertex.color = 2;
		}
	}

	void printPath( Vertex s, Vertex v )
	{
		if (s == v)
		{
			visit(s);
			return;
		}
		if (v.pV == null)
			return;
		else
		{
			printPath(s, v.pV);
			visit(v);
		}
	}

	void dfs( List <Vertex> g )
	{
		for (Vertex vertex : g)
		{
			if (vertex.color == 0)
				dfs_visit(vertex);
		}
	}

	void dfs_visit( Vertex v )
	{
		v.color = 1;
		for (Edge edge : v.neighbors)
		{
			if (edge.v.color == 0)
			{
				edge.v.pV = v;
				dfs_visit(edge.v);
			}
		}
		v.color = 2;
	}

	void visit( Vertex node )
	{

	}
}

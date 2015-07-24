package com.mdeng.quiz.poj;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CatchCow
{
	static int minTime2( int n, int k )
	{
		boolean[] flags = new boolean[100001];
		int[] times = new int[100001];
		Queue <Integer> queue = new LinkedList <Integer>();
		queue.add(n);
		flags[n] = true;

		while (!queue.isEmpty())
		{
			int t = queue.remove().intValue();
			if (t == k)
				return times[t];
			if (t - 1 >= 0 && !flags[t - 1])
			{
				queue.add(Integer.valueOf(t - 1));
				flags[t - 1] = true;
				times[t - 1] = times[t] + 1;
			}
			if (t + 1 <= 100000 && !flags[t + 1])
			{
				queue.add(Integer.valueOf(t + 1));
				flags[t + 1] = true;
				times[t + 1] = times[t] + 1;
			}
			if (2 * t <= 100000 && !flags[2 * t])
			{
				queue.add(Integer.valueOf(2 * t));
				flags[2 * t] = true;
				times[2 * t] = times[t] + 1;
			}
		}
		return -1;
	}

	static int minTime( int n, int k )
	{
		boolean[] flags = new boolean[100001];
		Queue <Integer> queue = new LinkedList <Integer>();
		queue.add(n);
		flags[n] = true;
		int time = 0, removeCount = 1;

		while (!queue.isEmpty())
		{
			int nextLayerCount = 0;
			for (int i = 0; i < removeCount; i++)
			{
				int t = queue.remove().intValue();
				if (t == k)
					return time;
				// left
				if (t - 1 >= 0 && !flags[t - 1])
				{
					queue.add(Integer.valueOf(t - 1));
					flags[t - 1] = true;
					nextLayerCount++;
				}
				// right
				if (t + 1 <= 100000 && !flags[t + 1])
				{
					queue.add(Integer.valueOf(t + 1));
					flags[t + 1] = true;
					nextLayerCount++;
				}
				// *2
				if (2 * t <= 100000 && !flags[2 * t])
				{
					queue.add(Integer.valueOf(2 * t));
					flags[2 * t] = true;
					nextLayerCount++;
				}
			}
			removeCount = nextLayerCount;
			time++;
		}
		return time;
	}

	public static void main( String[] args )
	{
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		System.out.println(CatchCow.minTime2(n, k));
	}
}

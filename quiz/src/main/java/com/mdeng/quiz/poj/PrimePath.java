package com.mdeng.quiz.poj;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PrimePath
{
	public static void main( String[] args )
	{
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		byte cases = scanner.nextByte();
		for (byte i = 0; i < cases; i++)
		{
			findMinPrimePath(scanner.nextShort(), scanner.nextShort());
		}
	}

	static void findMinPrimePath( int start, int end )
	{
		Queue <Integer> queue = new LinkedList <Integer>();
		boolean[] flags = new boolean[10000];
		short[] costs = new short[10000];
		queue.add(start);
		flags[start] = true;

		while (!queue.isEmpty())
		{
			short s = queue.remove().shortValue();
			if (s == end)
			{
				System.out.println(costs[s]);
				return;
			}
			// 1000
			int t = (s / 1000);
			for (int i = 1; i <= 9; i++)
			{
				if (i != t)
				{
					int newNum = 1000 * i + (s - 1000 * t);
					if (!flags[newNum] && isPrime(newNum))
					{
						queue.add(newNum);
						flags[newNum] = true;
						costs[newNum] = (short) (costs[s] + 1);
					}
				}
			}
			// 100
			t = (s / 100) % 10;
			for (int i = 0; i <= 9; i++)
			{
				if (i != t)
				{
					int newNum = s - 100 * t + 100 * i;
					if (!flags[newNum] && isPrime(newNum))
					{
						queue.add(newNum);
						flags[newNum] = true;
						costs[newNum] = (short) (costs[s] + 1);
					}
				}
			}
			// 10
			t = (s / 10) % 10;
			for (int i = 0; i <= 9; i++)
			{
				if (i != t)
				{
					int newNum = s - 10 * t + 10 * i;
					if (!flags[newNum] && isPrime(newNum))
					{
						queue.add(newNum);
						flags[newNum] = true;
						costs[newNum] = (short) (costs[s] + 1);
					}
				}
			}
			// 1
			t = s % 10;
			for (int i = 0; i <= 9; i++)
			{
				if (i != t)
				{
					int newNum = s - t + i;
					if (!flags[newNum] && isPrime(newNum))
					{
						queue.add(newNum);
						flags[newNum] = true;
						costs[newNum] = (short) (costs[s] + 1);
					}
				}
			}
		}
		System.out.println("Impossible");
	}

	static boolean isPrime( int n )
	{
		for (int i = 2; i < n; i++)
		{
			if (n % i == 0)
				return false;
		}
		return true;
	}
}

package com.mdeng.quiz.poj;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NonzeroMultiple
{
	static void find( byte n )
	{
		Queue <Long> queue = new LinkedList <Long>();
		queue.add(Long.valueOf(1));
		while (!queue.isEmpty())
		{
			Long integer = queue.remove();
			byte r = (byte) (integer % n);
			if (r == 0)
			{
				System.out.println(integer);
				return;
			}
			queue.add(Long.valueOf(integer * 10));
			queue.add(Long.valueOf(integer * 10 + 1));
		}
	}

	public static void main( String[] args )
	{
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		byte next = -1;
		while ((next = scanner.nextByte()) != 0)
		{
			find(next);
		}
	}
}

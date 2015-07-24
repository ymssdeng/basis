package com.mdeng.quiz.dp;

public class DPMaxSum
{
	public int maxSum1( int[] arr )
	{
		int curSum = 0, maxSum = 0;
		for (int i = 0; i < arr.length; i++)
		{
			if (curSum + arr[i] < 0)
				curSum = 0;
			else
			{
				curSum += arr[i];
				maxSum = curSum > maxSum ? curSum : maxSum;
			}
		}
		return maxSum;
	}

	public int maxSum2( int[] arr )
	{
		int[] t = new int[arr.length];
		int maxSum = t[0] = arr[0] > 0 ? arr[0] : 0;
		for (int i = 1; i < t.length; i++)
		{
			if (arr[i] + t[i - 1] > 0)
				t[i] = arr[i] + t[i - 1];
			else
				t[i] = 0;
			if (t[i] > maxSum)
				maxSum = t[i];
		}
		return maxSum;
	}

	public static void main( String[] args )
	{
		DPMaxSum sum = new DPMaxSum();
		int[] a1 = new int[]{-1};
		System.out.println("1: " + sum.maxSum1(a1) + ", 2: " + sum.maxSum2(a1));
		a1 = new int[]{-1, 3};
		System.out.println("1: " + sum.maxSum1(a1) + ", 2: " + sum.maxSum2(a1));
		a1 = new int[]{-1, 2, 4, -1, 4, 2, -9};
		System.out.println("1: " + sum.maxSum1(a1) + ", 2: " + sum.maxSum2(a1));
		a1 = new int[]{1, 0, -11, 3, 2, 9, -8, 0, 12, -81, 0, -98, 0, 11};
		System.out.println("1: " + sum.maxSum1(a1) + ", 2: " + sum.maxSum2(a1));
	}
}

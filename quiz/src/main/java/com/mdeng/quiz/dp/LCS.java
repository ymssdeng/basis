package com.mdeng.quiz.dp;

public class LCS <T extends Comparable <T>>
{
	int lcs( T[] a1, T[] a2 )
	{
		int[][] table = new int[a1.length + 1][a2.length + 1];
		int largestLen = 0;

		for (int i = 1; i <= a1.length; i++)
		{
			for (int j = 1; j <= a2.length; j++)
			{
				if (a1[i - 1].compareTo(a2[j - 1]) == 0)
				{
					table[i][j] = table[i - 1][j - 1] + 1;
					largestLen = largestLen < table[i][j] ? table[i][j]
						: largestLen;
				}
				else
				{
					table[i][j] = 0;
				}
			}
		}

		return largestLen;
	}

	int lcs2( T[] x, T[] y )
	{
		int[][] table = new int[x.length + 1][y.length + 1];
		int ret = 0;
		for (int i = 1; i <= x.length; i++)
		{
			for (int j = 1; j <= y.length; j++)
			{
				if (x[i].compareTo(y[j]) == 0)
				{
					table[i][j] = table[i - 1][j - 1] + 1;
					ret = table[i][j] > ret ? table[i][j] : ret;
				}
				else
					table[i][j] = 0;
			}
		}
		return ret;
	}

	int lcs3( int[] x )
	{
		int[] c = new int[x.length];
		c[0] = x[0] > 0 ? x[0] : 0;
		int ret = c[0];
		for (int i = 1; i < x.length; i++)
		{
			if (x[i] + c[i - 1] >= 0)
				c[i] = x[i] + c[i - 1];
			else
				c[i] = c[i - 1];
			ret = ret < c[i] ? c[i] : ret;
		}
		return ret;
	}
}

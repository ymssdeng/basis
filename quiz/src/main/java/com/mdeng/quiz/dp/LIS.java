package com.mdeng.quiz.dp;

public class LIS
{
	public static int lis( int[] x )
	{
		int[] c = new int[x.length];
		c[0] = 1;
		for (int i = 1; i < x.length; i++)
		{
			c[i] = c[i - 1];
			for (int j = i - 1; j >= 0; j--)
			{
				if (x[i] >= x[j])
				{
					c[i] = c[j] + 1;
					break;
				}
			}
		}

		return c[c.length - 1];
	}

	public static int lis2( int[] x )
	{
		int[] c = new int[x.length];
		c[0] = 1;
		int ret = 0;
		for (int i = 1; i < x.length; i++)
		{
			int max = 0;
			for (int j = i - 1; j >= 0; j--)
			{
				if (x[i] > x[j] && (c[j] + 1 > max))
				{
					max = c[j] + 1;
				}
			}
			c[i] = max > 0 ? max : 1;
			if (ret < c[i])
				ret = c[i];
		}
		return ret;
	}

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		int[] test1 = new int[]{1, 0, 2, 5, 3, 4, 6};
		int[] test2 = new int[]{1, 0, 0, 0, 2, 1};
		int[] test3 = new int[]{-5, -4, -3, 1, -6, -2};
		System.out.println(lis2(test1));
		System.out.println(lis2(test2));
		System.out.println(lis2(test3));

	}

}

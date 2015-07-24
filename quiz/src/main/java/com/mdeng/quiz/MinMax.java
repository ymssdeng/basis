package com.mdeng.quiz;

public class MinMax
{
	int min;
	int max;

	public void getMinMax( int[] arr )
	{
		min = max = arr[0];
		for (int i = 0; i < arr.length; i++)
		{
			int tmin = arr[i], tmax = arr[i];
			if (i + 1 < arr.length)
			{
				if (arr[i] < arr[i + 1])
					tmax = arr[i + 1];
				else
					tmin = arr[i + 1];
				i++;
			}
			if (tmin < min)
				min = tmin;
			if (tmax > max)
				max = tmax;
		}
	}

	public static void main( String[] args )
	{
		MinMax minMax = new MinMax();
		minMax.getMinMax(new int[]{1});
		System.out.println(minMax.min + ", " + minMax.max);
		minMax.getMinMax(new int[]{2, 1});
		System.out.println(minMax.min + ", " + minMax.max);
		minMax.getMinMax(new int[]{2, 1, 3});
		System.out.println(minMax.min + ", " + minMax.max);
		minMax.getMinMax(new int[]{1, 4, 2, 3});
		System.out.println(minMax.min + ", " + minMax.max);
		minMax.getMinMax(new int[]{2, 1, 4, 0, 5});
		System.out.println(minMax.min + ", " + minMax.max);
		minMax.getMinMax(new int[]{1, 3, 8, 2, -1, 6});
		System.out.println(minMax.min + ", " + minMax.max);
	}
}

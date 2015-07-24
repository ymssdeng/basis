package com.mdeng.quiz.search;

import com.mdeng.quiz.sort.QuickSort;

public class BinarySearch {

	public static <T extends Comparable<T>>
	T binarySearch(T[] t, int l, int r, T k)
	{
		QuickSort.sort(t, l , r);
		int q = (l+r)/2;
		while(q<=r)
		{
			if (t[q].compareTo(k) < 0)
				l = q+1;
			else if (t[q].compareTo(k) > 0)
				r = q-1;
			else
				return k;
			q = (l+r)/2;
		}
		return null;
	}
}

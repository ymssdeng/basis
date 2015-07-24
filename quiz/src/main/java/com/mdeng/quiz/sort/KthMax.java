package com.mdeng.quiz.sort;

public class KthMax {

	public static <T extends Comparable<T>>
	T kthMax(T[] t, int l, int r, int k)
	{
		if (l > r || k<=0)
			return null;
		if ( l == r)
			return t[l];
		
		int q = QuickSort.partition(t, l, r);
		int n = r-q+1;
		if (n == k)
			return t[q];
		if (n < k)
			return kthMax(t, l, q-1, k-n);
		else
			return kthMax(t, q+1, r, k);
	}
}

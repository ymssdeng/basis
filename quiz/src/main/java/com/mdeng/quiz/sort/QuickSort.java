package com.mdeng.quiz.sort;

public class QuickSort {
	
	public static <T extends Comparable<T>>
	void sort(T[] t, int l, int r)
	{
		if (l<r)
		{
			int q = partition(t, l, r);
			sort(t, l, q-1);
			sort(t, q+1, r);
		}
	}
	
	public static <T extends Comparable<T>>
	int partition(T[] t, int l, int r)
	{
		int j=l, i=j-1;
		for(;j<r;j++)
		{
			if (t[j].compareTo(t[r]) < 0)
			{
				i++;
				T tmp = t[i];
				t[i] = t[j];
				t[j] = tmp;
			}
		}
		T tmp = t[i+1];
		t[i+1] = t[r];
		t[r] = tmp;
		return i+1;
	}
}

package com.mdeng.quiz.sort;

public class MergeSort {
	
	public static <T extends Comparable<T>> void sort(T[] t, int l, int r)
	{
		if (l<r)
		{
			int q = (l+r)/2;
			sort(t, l, q);
			sort(t, q+1, r);
			merge(t, l, q+1, r);
		}
	}
	
	public static <T extends Comparable<T>> void merge(T[] t, int l, int m, int r)
	{
		T[] left = (T[]) new Comparable[m-l];
		T[] right = (T[]) new Comparable[r-m+1];
		
		for (int j=0, i=l;i<m;i++)
			left[j++] = t[i];
		for (int j=0, i=m;i<=r;i++)
			right[j++] = t[i];
		
		int c=l, c1=0, c2=0;
		while(c1<left.length && c2<right.length)
		{
			if(left[c1].compareTo(right[c2])<=0)
				t[c++] = left[c1++];
			else
				t[c++] = right[c2++];
		}
		while(c1<left.length)
			t[c++] = left[c1++];
		while(c2<right.length)
			t[c++] = right[c2++];
	}

}

package com.mdeng.quiz.sort;

public class HeapSort<T extends Comparable<T>>
{
	int heapSize;
	
	public void buildHeap(T[] t)
	{
		heapSize = t.length;
		for (int i=t.length/2-1;i>=0;i--)
			maintainHeap(t, i);
	}
	
	public void maintainHeap(T[] t, int k)
	{
		T tmp = t[k];
		for (int i=2*k+1;i<heapSize;)
		{
			if (i!=heapSize-1 && t[i].compareTo(t[i+1]) < 0)
				i++;
			if (t[i].compareTo(t[k]) <= 0)
				break;
			else
			{
				T tmp2 = t[i];
				t[i] = t[k];
				t[k] = tmp2;
			}
			k = i;
		}
		t[k] = tmp;
	}
	
	public void sort(T[] t)
	{
		buildHeap(t);
		for (int i=t.length;i>1;i--)
		{
			T tmp = t[0];
			t[0] = t[heapSize-1];
			t[heapSize-1] = tmp;
			heapSize--;
			maintainHeap(t, 0);
		}
	}
	
	public static void main(String[] args)
	{
		Integer[] test = new Integer[]{4, 2, 1, 6, 4, 8, 0, 5};
		HeapSort heap = new HeapSort();
		heap.sort(test);
		for(Integer i : test)
			System.out.println(i);
	}
}

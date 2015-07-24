package com.mdeng.quiz.sort;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.mdeng.quiz.sort.KthMax;
import com.mdeng.quiz.sort.MergeSort;
import com.mdeng.quiz.sort.QuickSort;

public class SortTest {

	@Test
	public void t()
	{
		int i = (-18)%13;
		int j = ((7-9)*10+2)%13;
		return;
	}
	
	@Test
	public void test() {
		Integer[] test = new Integer[] {0};
		Integer[] ret = new Integer[] {0};
		
		MergeSort.sort(test, 0, test.length-1);
		Assert.assertArrayEquals(ret, test);
		
		test = new Integer[] {0};
		QuickSort.sort(test, 0, test.length-1);
		Assert.assertArrayEquals(ret, test);
		
		test = new Integer[] {0};
		//HeapSort.sort(test, 0, test.length-1);
		//Assert.assertArrayEquals(ret, test);
		
	}

	@Test
	public void test1() {
		Integer[] test = new Integer[] {0 , 1, 2};
		Integer[] ret = new Integer[] {0, 1, 2};
		
		MergeSort.sort(test, 0, test.length-1);
		Assert.assertArrayEquals(ret, test);
		
		test = new Integer[] {0 , 1, 2};
		QuickSort.sort(test, 0, test.length-1);
		Assert.assertArrayEquals(ret, test);
		
		test = new Integer[] {0 , 1, 2};
		//HeapSort.sort(test, 0, test.length-1);
		//Assert.assertArrayEquals(ret, test);
		
	}
	
	@Test
	public void test3() {
		Integer[] test = new Integer[] {-6, 3, 2, 1, -1, -4, 5, 10, 9, 7};
		Integer[] ret = new Integer[] {-6, -4, -1, 1, 2, 3, 5, 7, 9, 10};
		
		MergeSort.sort(test, 0, test.length-1);
		Assert.assertArrayEquals(ret, test);
		
		test = new Integer[]{-6, 3, 2, 1, -1, -4, 5, 10, 9, 7};
		QuickSort.sort(test, 0, test.length-1);
		Assert.assertArrayEquals(ret, test);
		
		test = new Integer[]{-6, 3, 2, 1, -1, -4, 5, 10, 9, 7};
		//HeapSort.sort(test, 0, test.length-1);
		//Assert.assertArrayEquals(ret, test);
		
	}
	
	@Test
	public void test4() {
		Integer[] test = new Integer[] {5, 4, 3, 1, 0, -1, -2};
		Integer[] ret = new Integer[] {-2, -1, 0 , 1, 3, 4, 5};
		
		MergeSort.sort(test, 0, test.length-1);
		Assert.assertArrayEquals(ret, test);
		
		test = new Integer[] {5, 4, 3, 1, 0, -1, -2};
		QuickSort.sort(test, 0, test.length-1);
		Assert.assertArrayEquals(ret, test);
		
		test = new Integer[] {5, 4, 3, 1, 0, -1, -2};
		//HeapSort.sort(test, 0, test.length-1);
		//Assert.assertArrayEquals(ret, test);
		
	}
	
	@Test
	public void test5() {
		Integer[] test = new Integer[] {5, 4, 3, 1, 0, -1, -2};
		
		int r = KthMax.kthMax(test, 0, test.length-1, 1);
		assertEquals(5,r);
		r = KthMax.kthMax(test, 0, test.length-1, 2);
		assertEquals(4,r);
		r = KthMax.kthMax(test, 0, test.length-1, 3);
		assertEquals(3,r);
		r = KthMax.kthMax(test, 0, test.length-1, 4);
		assertEquals(1,r);
		r = KthMax.kthMax(test, 0, test.length-1, 5);
		assertEquals(0,r);
		r = KthMax.kthMax(test, 0, test.length-1, 6);
		assertEquals(-1,r);
		r = KthMax.kthMax(test, 0, test.length-1, 7);
		assertEquals(-2,r);
		
	}
}

package com.mdeng.quiz;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import junit.framework.Assert;

import org.junit.Test;

import com.mdeng.quiz.StackQueue;

public class StackQueueTest {
	@Test
	public void test()
	{
		StackQueue<Integer> aQueue = new StackQueue<>();
		aQueue.add(1);
		aQueue.add(2);
		
		assertEquals(1, aQueue.remove().intValue());
		assertEquals(2, aQueue.remove().intValue());
		//aQueue.remove();
		
		aQueue.add(3);
		aQueue.add(4);
		assertEquals(3, aQueue.remove().intValue());
		assertEquals(4, aQueue.remove().intValue());
		
		aQueue.add(5);
		aQueue.add(6);
		assertEquals(5, aQueue.remove().intValue());
		aQueue.add(7);
		aQueue.add(8);
		assertEquals(6, aQueue.remove().intValue());
		assertEquals(7, aQueue.remove().intValue());
		aQueue.add(9);
		assertEquals(8, aQueue.remove().intValue());
		assertEquals(9, aQueue.remove().intValue());
		aQueue.remove();
	}
	
	@Test
	public void test1()
	{
		System.out.println(Integer.toBinaryString(0x80000000));
		System.out.println(0x80000000);
		System.out.println(Integer.MAX_VALUE);
		for (int i = 1; i < 35; i++) {
			System.out.println(1<<i);
		}
		
	}
}

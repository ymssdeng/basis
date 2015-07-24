package com.mdeng.quiz.graph;

import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

public class GraphTest {

	
	//@Test
	public void test()
	{
		Queue<A> queue = new PriorityQueue<>();
		A a1 = new A(1);
		queue.add(a1);
		A a2 = new A(3);
		A a3 = new A(6);
		A a4 = new A(0);
		queue.add(a2);
		queue.add(a3);
		queue.add(a4);
		
		a4.a = -8;
		while (!queue.isEmpty()) {
			System.out.println(queue.remove().a);
			
		}
		
		A a = new A(100);
		foo(a);
		System.out.println(a.a);
	}

	private void foo(A a) {
		a = new A(200);
	}
	

	@Test
	public void test1()
	{
		System.out.println(f(1));
		System.out.println(f(0));
		System.out.println(f(1783));
		System.out.println(f(190));
		System.out.println(f(567000));
		System.out.println(f(40560));
		System.out.println(f(40056));
	}
	
	public int f(int x)
	{
		int ret = 0;
		while (x!=0) {
			int t = x%10;
			x = x/10;
			ret = ret*10+t;
		}
		return ret;
	}
}

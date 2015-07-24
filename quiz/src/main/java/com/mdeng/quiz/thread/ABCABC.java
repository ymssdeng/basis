package com.mdeng.quiz.thread;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ABCABC {

	static class A implements Runnable
	{
		private BlockingQueue<String> queue;
		A(BlockingQueue<String> queue)
		{
			this.queue = queue;
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					System.out.print(queue.take());
					queue.put("B");
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}
		
	}
	
	static class B implements Runnable
	{
		private BlockingQueue<String> queue;
		B(BlockingQueue<String> queue)
		{
			this.queue = queue;
		}
		
		@Override
		public void run() {
			while (true) {
				String s;
				try {
					queue.put("A");
					System.out.print(queue.take());
					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		SynchronousQueue<String> queue = new SynchronousQueue<>();
		
		/*A a = new A(queue);
		Thread threadA = new Thread(a);
		B b = new B(queue);
		Thread threadB = new Thread(b);
		threadA.start();
		threadB.start();*/
		new Thread(new ABC(0)).start();
		new Thread(new ABC(1)).start();
		new Thread(new ABC(2)).start();

	}

	static class ABC implements Runnable
	{
		private static String[] strs = new String[]{"A", "B", "C"};
		private static Object object = new Object();
		private static int count=0;
		private int cur;
		ABC(int cur)
		{
			this.cur = cur;
		}
		@Override
		public void run() {
			try {
				while (true) {
					synchronized (object) {
						
						if (cur == count)
						{
							System.out.print(strs[count]);
							count = (count+1)%3;
							object.notifyAll();
						}
						else
							object.wait();
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
}

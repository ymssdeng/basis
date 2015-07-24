package com.mdeng.quiz.thread;

public class ToInterrutedException {

	static class Task implements Runnable
	{

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException occurred.");
				Thread.currentThread().interrupt();//reset
			}
			
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(new Task());
		thread.start();
		thread.interrupt();

	}

}

package com.mdeng.quiz.pattern;

public class SingletonB {
	private volatile static SingletonB instance = null;
	
	private SingletonB(){}
	
	public static SingletonB getInstance()
	{
		if (instance == null)
		{
			synchronized (SingletonB.class) 
			{
				if (instance == null)
				{
					instance = new SingletonB();
				}
			}
		}
		
		return instance;
	}
}

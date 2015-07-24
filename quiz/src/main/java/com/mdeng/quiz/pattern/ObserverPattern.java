package com.mdeng.quiz.pattern;


import java.util.Observable;
import java.util.Observer;

public class ObserverPattern {
	class ConcreteSubject extends Observable
	{
		
	}
	
	class ConcreteObserver implements Observer
	{

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String[] args)
	{
		ObserverPattern pattern = new ObserverPattern();
		
		ConcreteSubject subject = pattern.new ConcreteSubject();
		ConcreteObserver observer = pattern.new ConcreteObserver();
		
		subject.addObserver(observer);
		
		subject.notifyObservers();
	}
}

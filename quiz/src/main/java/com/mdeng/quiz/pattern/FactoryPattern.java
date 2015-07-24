package com.mdeng.quiz.pattern;

public class FactoryPattern {

	interface Product
	{
		void operation();
	}
	
	abstract class AbstractFactory
	{
		abstract Product create();
	}
	
	class ConcreteProductA implements Product
	{

		@Override
		public void operation() {
		}
		
	}
	
	class ConcreteFactoryA extends AbstractFactory
	{

		@Override
		Product create() {
			return new ConcreteProductA();
		}
		
	}
	
	class ConcreteProductB implements Product
	{

		@Override
		public void operation() {
		}
		
	}
	
	class ConcreteFactoryB extends AbstractFactory
	{

		@Override
		Product create() {
			return new ConcreteProductA();
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FactoryPattern pattern = new FactoryPattern();
		AbstractFactory factory = pattern.new ConcreteFactoryA();
		Product a = factory.create();
		AbstractFactory factory2 = pattern.new ConcreteFactoryB();
		Product b = factory2.create();

	}

}

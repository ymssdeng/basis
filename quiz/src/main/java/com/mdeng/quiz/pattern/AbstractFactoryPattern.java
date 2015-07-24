package com.mdeng.quiz.pattern;



public class AbstractFactoryPattern {

	abstract class AbstractFactoty
	{
		abstract ProductA createProductA();
		abstract ProductB createProductB();
	}
	
	abstract class ProductA
	{
		abstract void operation();
	}
	
	abstract class ProductB
	{
		abstract void operation();
	}
	
	class ConcreteProductA1 extends ProductA
	{

		@Override
		void operation() {
			
		}
		
	}

	class ConcreteProductA2 extends ProductA
	{

		@Override
		void operation() {
			
		}
		
	}

	class ConcreteProductB1 extends ProductB
	{

		@Override
		void operation() {
			
		}
		
	}

	class ConcreteProductB2 extends ProductB
	{

		@Override
		void operation() {
			
		}
		
	}
	
	class ConcreteFactory1 extends AbstractFactoty
	{

		@Override
		ProductA createProductA() {
			return new ConcreteProductA1();
		}

		@Override
		ProductB createProductB() {
			return new ConcreteProductB1();
		}
		
	}
	
	class ConcreteFactory2 extends AbstractFactoty
	{

		@Override
		ProductA createProductA() {
			return new ConcreteProductA2();
		}

		@Override
		ProductB createProductB() {
			return new ConcreteProductB2();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractFactoryPattern pattern = new AbstractFactoryPattern();
		AbstractFactoty factoty = pattern.new ConcreteFactory1();
		ProductA a = factoty.createProductA();
		ProductB b = factoty.createProductB();
	}

}

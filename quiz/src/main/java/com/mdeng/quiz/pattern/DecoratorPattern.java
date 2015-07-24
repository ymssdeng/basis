package com.mdeng.quiz.pattern;

/**
 * Attach additional responsibilities to an object dynamically
 */
public class DecoratorPattern {

	interface Component
	{
		void operation();
	}
	
	
	class ConcreteComponent implements Component
	{
		@Override
		public void operation() {
		}	
	}
	
	abstract class Decorator implements Component
	{
		Component component;
		public Decorator(Component component) {
			this.component = component;
		}
	}
	
	class ConcreteDecoratorA extends Decorator
	{
		public ConcreteDecoratorA(Component component) {
			super(component);
		}

		@Override
		public void operation() {
			component.operation();
			decoratorOperation();
		}
		
		private void decoratorOperation() {
		}
	}
	
	class ConcreteDecoratorB extends Decorator
	{
		public ConcreteDecoratorB(Component component) {
			super(component);
		}

		@Override
		public void operation() {
			component.operation();
			decoratorOperation();
		}
		
		private void decoratorOperation() {
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecoratorPattern pattern = new DecoratorPattern();
		
		ConcreteComponent component = pattern.new ConcreteComponent();
		ConcreteDecoratorA a = pattern.new ConcreteDecoratorA(component);
		ConcreteDecoratorB b = pattern.new ConcreteDecoratorB(a);
		
		b.operation();
	}

}

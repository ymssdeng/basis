package com.mdeng.quiz.pattern;

public class StrategyPattern
{
	interface Strategy
	{
		Object calculate( Object object );
	}

	class ConcreteStrategyA implements Strategy
	{

		@Override
		public Object calculate( Object object )
		{
			return object.toString();
		}

	}

	class ConcreteStrategyB implements Strategy
	{

		@Override
		public Object calculate( Object object )
		{
			return object.hashCode();
		}

	}

	class Context
	{
		Strategy strategy = null;

		public Context( Strategy strategy )
		{
			this.strategy = strategy;
		}

		public Object operation( Object object )
		{
			return strategy.calculate(object);
		}
	}

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		StrategyPattern pattern = new StrategyPattern();
		Strategy strategyA = pattern.new ConcreteStrategyA();
		Strategy strategyB = pattern.new ConcreteStrategyB();

		Context context = pattern.new Context(strategyA);
		context.operation(1);
		context = pattern.new Context(strategyB);
		context.operation(1);

	}

}

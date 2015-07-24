package com.mdeng.quiz.pattern;

public class TemplateMethodPattern
{
	abstract class Template
	{
		// template method
		void operation()
		{
			step1();
			step2();
		}

		abstract void step1();

		abstract void step2();
		// ...
	}

	class ConcreteA extends Template
	{
		@Override
		void step1()
		{
		}

		@Override
		void step2()
		{
		}
	}

	class ConcreteB extends Template
	{
		@Override
		void step1()
		{
		}

		@Override
		void step2()
		{
		}
	}

	public static void main( String[] args )
	{
		TemplateMethodPattern pattern = new TemplateMethodPattern();
		Template template = pattern.new ConcreteA();
		template.operation();
	}
}

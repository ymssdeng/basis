package com.mdeng.quiz.pattern;


public class BuilderPattern {
	interface Builder
	{
		void buildPart();
		//...
	}
	
	class Product
	{
		
	}
	
	class ConcreteBuilder implements Builder
	{
		private Product product = null;
		@Override
		public void buildPart() {
			// TODO Auto-generated method stub
			
		}
		
		public Product getResult() {
			return product;
		}
	}
	
	class Director
	{
		Builder builder = null;
		public Director(Builder builder) {
			this.builder = builder;
		}
		
		public void build() {
			builder.buildPart();
			//...
		}
	}
	
	public static void main(String[] args)
	{
		BuilderPattern pattern = new BuilderPattern();
		
		ConcreteBuilder builder = pattern.new ConcreteBuilder();
		Director director = pattern.new Director(builder);
		
		director.build();
		Product product = builder.getResult();
	}
}

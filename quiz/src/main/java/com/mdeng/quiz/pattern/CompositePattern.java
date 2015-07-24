package com.mdeng.quiz.pattern;

import java.util.ArrayList;
import java.util.Collection;

public class CompositePattern {

	//graphic
	abstract class Component
	{
		abstract void operation();
		protected void add(Component component){};
		protected void remove(Component component){};
		protected int getChild(){return 0;};
	}
	
	//line
	class LeafA extends Component
	{

		@Override
		void operation() {
		}
		
	}
	
	//picture
	class Composite extends Component
	{
		Collection<Component> composites = new ArrayList<>();
		@Override
		void operation() {
		}
		
		@Override
		protected
		void add(Component component) {
			composites.add(component);
		}
		
		@Override
		protected
		void remove(Component component) {
			composites.remove(component);
		}
		
		@Override
		protected
		int getChild() {
			return composites.size();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

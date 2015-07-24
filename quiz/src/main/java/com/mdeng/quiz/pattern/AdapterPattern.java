package com.mdeng.quiz.pattern;

public class AdapterPattern
{
	// new interface
	interface Request
	{
		void request();
	}

	// what we have, but doesn't match what we need
	class Adaptee
	{
		public void specificRequest()
		{

		}
	}

	class ClassAdapter extends Adaptee implements Request
	{

		@Override
		public void request()
		{
			// ...
			specificRequest();
			// ...
		}

	}

	class ObjectAdapter implements Request
	{
		Adaptee adaptee = null;

		public ObjectAdapter( Adaptee adaptee )
		{
			this.adaptee = adaptee;
		}

		@Override
		public void request()
		{
			adaptee.specificRequest();
		}

	}

	public static void main( String[] args )
	{
		AdapterPattern pattern = new AdapterPattern();

		// class
		ClassAdapter classAdapter = pattern.new ClassAdapter();
		classAdapter.request();

		// object
		Adaptee adaptee = pattern.new Adaptee();
		ObjectAdapter objectAdapter = pattern.new ObjectAdapter(adaptee);
		objectAdapter.request();
	}
}

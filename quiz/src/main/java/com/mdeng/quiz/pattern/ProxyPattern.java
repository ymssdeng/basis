package com.mdeng.quiz.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPattern
{
	interface Subject
	{
		void request();
	}

	class RealSubject implements Subject
	{

		@Override
		public void request()
		{
			// TODO Auto-generated method stub

		}

	}

	class ProxySubject implements Subject
	{
		private Subject subject = null;

		public ProxySubject( Subject subject )
		{
			this.subject = subject;
		}

		@Override
		public void request()
		{
			// pre-request
			subject.request();
			// post-request
		}
	}

	class DynamicProxy implements InvocationHandler
	{
		private Object proxied;

		public DynamicProxy( Object proxied )
		{
			this.proxied = proxied;
		}

		@Override
		public Object invoke( Object proxy, Method method, Object[] args )
			throws Throwable
		{
			// pre-request...
			return method.invoke(proxied, args);
		}

	}

	public static void main( String[] args )
	{
		ProxyPattern pattern = new ProxyPattern();

		Subject realSubject = pattern.new RealSubject();
		Subject proxySubject = pattern.new ProxySubject(realSubject);

		// client
		proxySubject.request();

		// dynamic proxy
		Subject proxy = (Subject) Proxy.newProxyInstance(Subject.class
			.getClassLoader(), new Class[]{Subject.class},
			pattern.new DynamicProxy(realSubject));
		proxy.request();
	}
}

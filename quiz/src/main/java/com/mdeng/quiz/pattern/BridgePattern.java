package com.mdeng.quiz.pattern;

public class BridgePattern
{
	class Windows
	{
		WindowsDrawImpl impl;

		public Windows( WindowsDrawImpl impl )
		{
			this.impl = impl;
		}

		void drawText()
		{
			impl.drawText();
		}

		void drawRect()
		{
			impl.drawLine();
			impl.drawLine();
			impl.drawLine();
			impl.drawLine();
		}
	}

	interface WindowsDrawImpl
	{
		void drawText();

		void drawLine();
	}

	class TransientWindows extends Windows
	{
		public TransientWindows( WindowsDrawImpl impl )
		{
			super(impl);
		}

		void drawCloseBox()
		{
			// ...
			drawRect();
			// ...
		}
	}

	class XPDrawImpl implements WindowsDrawImpl
	{
		@Override
		public void drawText()
		{
		}

		@Override
		public void drawLine()
		{
		}
	}

	class SUSEDrawImpl implements WindowsDrawImpl
	{
		@Override
		public void drawText()
		{
		}

		@Override
		public void drawLine()
		{
		}
	}

	public static void main( String[] args )
	{
		BridgePattern pattern = new BridgePattern();

		WindowsDrawImpl windowsImpl = pattern.new XPDrawImpl();
		TransientWindows windows = pattern.new TransientWindows(windowsImpl);

		windows.drawCloseBox();
	}
}

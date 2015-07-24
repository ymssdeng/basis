package com.mdeng.quiz.pattern;

public class FacadePattern {

	//subsystem
	class Scanner
	{
		void scan(){}
	}
	
	//subsystem
	class Parser
	{
		void parse(){}
	}
	
	//facade
	class Compiler
	{
		Scanner scanner;
		Parser parser;
		
		public void compile()
		{
			scanner.scan();
			parser.parse();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

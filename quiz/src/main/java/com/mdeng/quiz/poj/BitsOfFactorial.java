package com.mdeng.quiz.poj;

public class BitsOfFactorial
{
	public static void main( String[] args ) throws Exception
	{
		/*
		 * Scanner scanner = new Scanner(new BufferedInputStream(System.in)); int cases = scanner.nextInt();
		 * 
		 * for (int i = 0; i < cases; i++) { long one = scanner.nextLong(); double ret = 1; for (long j = 1; j <= one; j++) { ret += Math.log10(j); }
		 * ret = Math.floor(ret); System.out.println((int) ret); }
		 */

		BitsOfFactorial t = new BitsOfFactorial();
		System.out.println(t.foo("zz"));

	}

	char foo( String str ) throws Exception
	{
		int[] arr = new int[26];

		for (int i = 0; i < str.length(); i++)
		{
			arr[str.charAt(i) - 'a']++;
		}

		for (int i = 0; i < str.length(); i++)
		{
			if (arr[str.charAt(i) - 'a'] == 1)
				return str.charAt(i);
		}

		throw new Exception("No such char in given string.");
	}
}

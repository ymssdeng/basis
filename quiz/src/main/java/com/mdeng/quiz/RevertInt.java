package com.mdeng.quiz;

public class RevertInt {

	static long revert(int n)
	{
		long r = 0;
		while (n!=0) {
			r = r*10 + n%10;
			n/=10;			
		}
		return r;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(revert(123));
		System.out.println(revert(1230));
		System.out.println(revert(10023));
		System.out.println(revert(Integer.MAX_VALUE));
		System.out.println(revert(-123));
		System.out.println(revert(Integer.MIN_VALUE));

	}

}

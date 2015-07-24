package com.mdeng.quiz;

import java.util.Arrays;

public class BigSum {

	static String sum(String a, String b)
	{
		int lena = a.length(), lenb = b.length();
		int maxlen = lena<lenb?lenb:lena;
		char[] ret = new char[maxlen+1];
		boolean flag = false;
		for(int ia = lena-1, ib = lenb-1, j=0; ia>=0||ib>=0; ia--,ib--,j++)
		{
			int n1 = 0, n2 = 0;
			if (ia>=0)
				n1 = a.charAt(ia) - '0';
			if (ib>=0)
				n2 = b.charAt(ib) - '0';
			int s = n1+n2;
			if (flag)
			{
				s++;
				flag = false;
			}
			if (s>=10)
			{
				s-=10;
				flag = true;
				if (ia<=0&&ib<=0)
					ret[j+1]='1';
			}
			
			ret[j] = (char) ('0'+s);
		}
		
		return new StringBuilder(new String(ret)).reverse().toString().trim();
	}
	
	static String sub(String a, String b)
	{
		int lena = a.length(), lenb = b.length();
		int maxlen = lena<lenb?lenb:lena;
		char[] ret = new char[maxlen];
		boolean flag = false;
		for(int ia = lena-1, ib = lenb-1, j=0; ia>=0||ib>=0; ia--,ib--,j++)
		{
			int n1 = 0, n2 = 0;
			if (ia>=0)
				n1 = a.charAt(ia) - '0';
			if (ib>=0)
				n2 = b.charAt(ib) - '0';
			int s = n1-n2;
			if (flag)
			{
				s--;
				flag = false;
			}
			if (s<0)
			{
				s+=10;
				flag = true;
			}
			if (s==0&&ia<=0&&ib<=0)
			{
				ret[j] = '\0';
			}
			else
				ret[j] = (char) ('0'+s);
		}
		
		String sub = new StringBuilder(new String(ret)).reverse().toString().trim();
		
		return sub.charAt(0)=='0'?"0":sub;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(sum("100","100"));
		System.out.println(sub("1000","1000"));

	}

}

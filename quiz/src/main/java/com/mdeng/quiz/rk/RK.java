package com.mdeng.quiz.rk;

public class RK {

	public void rk(String t, String p)
	{
		int d = 31, q = 11, thash = 0, phash = 0, n=t.length(), m = p.length();
		int h = foo(d, m-1, q);
		
		
		for(int i=0;i<m;i++)
		{
			phash = (d*phash + p.charAt(i))%q;
			thash = ((d*thash) + t.charAt(i))%q;
		}
		
		for (int i = 0;i<=n-m;i++)
		{
			if (phash == thash)
			{
				//p==t[i...i+m]?
				if (equal(t, p, i))
					System.out.println(i);
			}
			else if (i<n-m)
			{
				thash = (d*(thash - h*t.charAt(i)) + t.charAt(i+m))%q;
				if (thash<0)
					thash += q;
			}
		}
		
	}

	private int foo(int d, int i, int q) {
		int ret = 1;
		String s = Integer.toBinaryString(i);
		for (int j = 0; j < s.length(); j++) {
			ret = (ret * ret)%q;
			if (s.charAt(j) == '1')
				ret = (ret * d)%q;
		}
		return ret;
	}

	private boolean equal(String t, String p, int i) {
		for (int j = 0; j < p.length(); j++) {
			if (t.charAt(i+j) != p.charAt(j))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		RK rk = new RK();
		rk.rk("abcdefghijk", "");
	}
}

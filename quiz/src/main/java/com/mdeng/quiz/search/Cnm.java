package com.mdeng.quiz.search;


public class Cnm {
	static int[] ret;
	static int index=-1;

	public void cnm(int[] arr, int m) {
		int n = arr.length;
		ret = new int[m];
		//for (int i = 0; i <= n-m; i++) {
			cnm_dp(arr, 0,n,  m);
		//}
	}

	public void cnm_dp(int[] arr, int i, int n, int m) {
		if (index == m - 1) {
			print();
			return;
		}
		
		if (i>=n)
			return;

		for (int j = i; j <n; j++) {
			ret[++index] = arr[j];
			cnm_dp(arr, j + 1, n, m);
			index--;	
		}
	}

	private void print() {
		for (int i = 0; i < ret.length; i++) {
			System.out.print(ret[i]);
		}
		System.out.println();
	}

}

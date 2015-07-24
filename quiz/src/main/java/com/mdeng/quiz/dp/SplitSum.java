package com.mdeng.quiz.dp;

public class SplitSum {

	static int[][][] sum;
	static void split(int n)
	{
		sum = new int[n+1][n*n][n+1];
		sum[1][1][1]=1;
		sum[2][1][1]=1;sum[2][1][2]=1;
		int preCount = 1;
		for (int i = 3; i <=n; i++) {
			int count = preCount+i/2;
			for (int j = 1; j <= preCount; j++) {
					int k = 1;
					for (; sum[i-1][j][k]!=0; k++) {
						sum[i][j][k] = sum[i-1][j][k];
					}
					sum[i][j][k] = 1;	
			}
			for (int j = 1; j <= i/2; j++) {
				sum[i][j+preCount][1]=i-j;
				sum[i][j+preCount][2]=j;
			}
			preCount = count;
		}
		
		//print
		for (int j = 1; j<=preCount; j++) {
			System.out.printf("%d =", n);
			int k=1;
			while (true) {
				System.out.printf(" %d", sum[n][j][k++]);
				if (k<=n && sum[n][j][k]!=0)
					System.out.print(" +");
				else {
					break;
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		split(10);
	}

}

package com.mdeng.quiz.dp;

public class CoinsChange {

	public int makeChane(int[] coins, int money)
	{
		int[] ret = new int[money+1];
		for (int i = 1; i <= money; i++) {
			int min = i;
			for (int j = 0; j < coins.length; j++) {
				if (i-coins[j] >=0 && ret[i-coins[j]]+1<min)
					min = ret[i-coins[j]]+1;
			}
			ret[i] = min;
		}
		return ret[money];
	}
}

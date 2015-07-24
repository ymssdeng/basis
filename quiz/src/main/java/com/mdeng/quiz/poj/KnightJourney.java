package com.mdeng.quiz.poj;

/*
 * Description
 * 
 * Background The knight is getting bored of seeing the same black and white squares again and again and has decided to make a journey around the
 * world. Whenever a knight moves, it is two squares in one direction and one square perpendicular to this. The world of a knight is the chessboard he
 * is living on. Our knight lives on a chessboard that has a smaller area than a regular 8 * 8 board, but it is still rectangular. Can you help this
 * adventurous knight to make travel plans?
 * 
 * Problem Find a path such that the knight visits every square once. The knight can start and end on any square of the board. Input
 * 
 * The input begins with a positive integer n in the first line. The following lines contain n test cases. Each test case consists of a single line
 * with two positive integers p and q, such that 1 <= p * q <= 26. This represents a p * q chessboard, where p describes how many different square
 * numbers 1, . . . , p exist, q describes how many different square letters exist. These are the first q letters of the Latin alphabet: A, . . .
 * Output
 * 
 * The output for every scenario begins with a line containing "Scenario #i:", where i is the number of the scenario starting at 1. Then print a
 * single line containing the lexicographically first path that visits all squares of the chessboard with knight moves followed by an empty line. The
 * path should be given on a single line by concatenating the names of the visited squares. Each square name consists of a capital letter followed by
 * a number. If no such path exist, you should output impossible on a single line. Sample Input
 * 
 * 3 1 1 2 3 4 3 Sample Output
 * 
 * Scenario #1: A1
 * 
 * Scenario #2: impossible
 * 
 * Scenario #3: A1B3C1A2B4C2A3B1C3A4B2C4
 */
import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class KnightJourney
{
	// be careful to the sequence, you should not compare all possible solutions
	private static final int[][] steps = new int[][]{{-2, -1}, {-2, 1},
		{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
	static int maxP;
	static int maxQ;
	static boolean[][] flags = new boolean[27][27];
	static char[][] path = new char[27][2];
	static int currentStep;
	static boolean hasFoundSolution;

	static boolean isValid( int x, int y )
	{
		return x >= 1 && x <= maxQ && y >= 1 && y <= maxP;
	}

	static void dfs( int x, int y )
	{
		if (currentStep == maxP * maxQ)
		{
			print();
			hasFoundSolution = true;
			return;
		}

		for (int i = 0; i < 8; i++)
		{
			int nextX = x + steps[i][0];
			int nextY = y + steps[i][1];
			if (!isValid(nextX, nextY) || flags[nextX][nextY])
				continue;

			currentStep++;
			path[currentStep][0] = (char) ('A' + nextX - 1);
			path[currentStep][1] = (char) ('1' + nextY - 1);
			flags[nextX][nextY] = true;
			dfs(nextX, nextY);
			// if not return here, it will find all possible solutions
			if (hasFoundSolution)
				return;

			flags[nextX][nextY] = false;
			currentStep--;
		}
	}

	static void print()
	{
		for (int i = 1; i <= maxP * maxQ; i++)
		{
			System.out.printf("%c%c", path[i][0], path[i][1]);
		}
		System.out.printf("\n\n");
	}

	public static void main( String[] args )
	{

		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		int cases = scanner.nextInt();
		for (int i = 1; i <= cases; i++)
		{
			maxP = scanner.nextInt();
			maxQ = scanner.nextInt();

			// reset data each time
			for (int j = 0; j < 27; j++)
			{
				Arrays.fill(flags[j], false);
				Arrays.fill(path[j], '0');
			}
			flags[1][1] = true;
			path[1][0] = 'A';
			path[1][1] = '1';
			currentStep = 1;
			hasFoundSolution = false;

			System.out.printf("Scenario #%d:\n", i);
			dfs(1, 1);
			if (!hasFoundSolution)
				System.out.printf("impossible\n\n");

		}
	}
}

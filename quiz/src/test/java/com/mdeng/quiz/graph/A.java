package com.mdeng.quiz.graph;

public class A implements Comparable<A>{
int a;
public A(int i) {
	a = i;
}
@Override
public int compareTo(A o) {
	// TODO Auto-generated method stub
	return a>o.a?1:-1;
}
}

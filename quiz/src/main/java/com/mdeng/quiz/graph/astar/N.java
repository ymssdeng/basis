package com.mdeng.quiz.graph.astar;

public class N implements Comparable<N>{
  int x;
  int y;
  int[] cost;
  
  int g;
  int h;
  N parent;
  boolean visited;
  
  @Override
  public int compareTo(N o) {
    return Integer.valueOf(g+h).compareTo(o.g+o.h);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    N other = (N) obj;
    if (x != other.x) return false;
    if (y != other.y) return false;
    return true;
  }
  
  
}

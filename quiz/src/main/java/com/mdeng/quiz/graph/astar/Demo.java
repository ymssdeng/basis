package com.mdeng.quiz.graph.astar;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class Demo {
  
  public static void h1(Demo demo,N start, N end) {

    //Stopwatch stopwatch = new Stopwatch();
    //stopwatch.start();
    demo.astar(start, end, new H() {

      @Override
      public int h(N a, N b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
      }

    });
    //stopwatch.stop();
    //System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    //demo.print(end);
    System.out.println(demo.loopCount);
    demo.reset();
  }

  public static void h2(Demo demo,N start, N end) {

    //Stopwatch stopwatch = new Stopwatch();
    //stopwatch.start();
    demo.astar(start, end, new H() {

      @Override
      public int h(N a, N b) {
        return 0;
      }

    });
    //stopwatch.stop();
    //System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    //demo.print(end);
    System.out.println(demo.loopCount);
    demo.reset();
  }

  public static void main(String[] args) throws InterruptedException {
    Random r = new Random();
    for (int i = 0; i < 4; i++) {
      Demo demo = new Demo();
      N[][] g = demo.init();
      int x1 = r.nextInt(10);
      int y1 = r.nextInt(30);
      int x2 = r.nextInt(10);
      int y2 = r.nextInt(30);
      System.out.println(x1+","+y1+"-->"+x2+","+y2);
      h1(demo,g[x1][y1],g[x2][y2]);
      h2(demo,g[x1][y1],g[x2][y2]);  
    }
    
    System.exit(0);
  }

  // astar(start, end) {
  //   OPEN <- start, CLOSED <- empty
  //   while(OPEN!=empty) {
  //     get n from OPEN which has minimum f
  //     if(neighbor == end) break;
  //     for(neighbor in n.neighbors) {
  //       if(neighbor in CLOSED) continue;
  //       else {
  //         if(neighbor in OPEN) {
  //           if(n.g + cost(n,neighbor)<neighbor.g) {
  //             neighbor.g = n.g + cost(n,neighbor);
  //             neighbor.parent = n;
  //           }
  //         } else {
  //           neighbor.h = h(neighbor);
  //           neighbout.g = n.g + cost(n,neighbor);
  //           neighbor.parent = n;
  //           OPEN <- neighbor; 
  //         }
  //       }
  //     }
  //     CLOSED <- n
  //   }
  // }

  PriorityQueue<N> open = new PriorityQueue<N>();
  int[][] dir = { {0, -1}, {0, 1}, {-1, 0}, {1, 0}};// up down left right
  //int[] cost = {1, 2, 3, 4};
  int X = 10;
  int Y = 30;
  N[][] v;
  int[][] e;
  int loopCount;
  public void astar(N start, N end, H h) {
    open.offer(start);
    while (!open.isEmpty()) {
      N n = open.poll();
      if (n == end) {
        break;
      }

      for (int i = 0; i < dir.length; i++) {
        int x = n.x + dir[i][0];
        int y = n.y + dir[i][1];
        if (isValid(x, y)) {
          N k = v[x][y];

          if (k.visited)
            continue;
          else if (open.contains(k)) {
            if (n.g + n.cost[i] < k.g) {
              k.g = n.g + n.cost[i];
              k.parent = n;
            }
          } else {
            k.h = h.h(k, end);
            k.g = n.g + n.cost[i];
            k.parent = n;
            open.offer(k);
          }
          loopCount++;
        }
      }
      n.visited = true;
      //loopCount++;
    }
  }

  private boolean isValid(int x, int y) {
    return x >= 0 && x < X && y >= 0 && y < Y;
  }

  N[][] init() {
    v = new N[X][Y];

    Random random = new Random();
    for (int i = 0; i < X; i++) {
      for (int j = 0; j < Y; j++) {
        v[i][j] = new N();
        v[i][j].x = i;
        v[i][j].y = j;
        v[i][j].cost = new int[4];
        for (int k = 0; k < 4; k++) {
          int r = random.nextInt(11);
          r = r == 0 ? 1 : r;
          v[i][j].cost[k] = r;
        }
      }
    }
    return v;
  }

  int h(N a, N b) {
    // return 0;
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }

  void print(N end) {
    System.out.println("(" + end.x + "," + end.y + ")");
    if (end.parent != null) {
      print(end.parent);
    }
  }

  void reset() {
    open.clear();
    for (int i = 0; i < X; i++) {
      for (int j = 0; j < Y; j++) {
        v[i][j].g = 0;
        v[i][j].h = 0;
        v[i][j].parent = null;
        v[i][j].visited = false;
      }
    }
    loopCount=0;
  }
}

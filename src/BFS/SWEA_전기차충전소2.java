package BFS;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_전기차충전소2  {

   static int N, answer;
   static int[] dr = { -1, 1, 0, 0 };
   static int[] dc = { 0, 0, -1, 1 };
   static StringBuilder sb = new StringBuilder();

   static class Point {
      int r, c, d;

      Point(int r, int c, int d) {
         this.r = r;
         this.c = c;
         this.d = d;
      }
   }

   static List<Point> homeList;
   static List<Point> targetList;
   static int[][] map;

   public static void main(String[] args) throws IOException {
      Scanner sc = new Scanner(System.in);
      int T = sc.nextInt();
      for (int tc = 1; tc <= T; tc++) {
         N = sc.nextInt();
         map = new int[31][31];
         homeList = new ArrayList<>();
         targetList = new ArrayList<>();
         answer = Integer.MAX_VALUE;
         for (int i = 0; i < N; i++) {
            int x = sc.nextInt() + 15;
            int y = sc.nextInt() + 15;
            int d = sc.nextInt();
            map[y][x] = -1;

            homeList.add(new Point(y, x, d));
         }

         case1();

         if (answer == Integer.MAX_VALUE) {
            case2();
         }
         if (answer == Integer.MAX_VALUE) {
            answer = -1;
         }
         sb.append("#" + tc + " " + answer + '\n');
      }
      System.out.println(sb.toString());
   }

   static void case2() {
      for (int r = 0; r < 31; r++) {
         for (int c = 0; c < 31; c++) {
            if (map[r][c] >= 1) {
               targetList.add(new Point(r, c, 0));
            }
         }
      }

      for (int i = 0; i < targetList.size() - 1; i++) {
         for (int j = i + 1; j < targetList.size(); j++) {
            Point p1 = targetList.get(i);
            Point p2 = targetList.get(j);
            int cnt = 0;
            int dist = 0;
            for (Point home : homeList) {
               int d1 = calc(p1.r, p1.c, home.r, home.c);
               int d2 = calc(p2.r, p2.c, home.r, home.c);
               int minDist = Math.min(d1, d2);

               if (minDist > home.d) {
                  break;
               } else {
                  cnt++;
                  dist += minDist;
               }
            }

            if (cnt == N) {
               answer = Math.min(answer, dist);
            }
         }
      }
   }

   static void case1() {
      for (Point home : homeList) {
         Queue<Point> q = new ArrayDeque<>();
         q.add(home);
         boolean[][] visited = new boolean[31][31];
         visited[home.r][home.c] = true;

         for (int x = 0; x < home.d; x++) {
            int qSize = q.size();
            for (int y = 0; y < qSize; y++) {
               Point p = q.poll();
               for (int i = 0; i < 4; i++) {
                  int nr = p.r + dr[i];
                  int nc = p.c + dc[i];
                  if (checkRange(nr, nc) && !visited[nr][nc] && map[nr][nc] != -1) {
                     map[nr][nc]++;
                     q.add(new Point(nr, nc, 0));
                     visited[nr][nc] = true;
                  }
               }
            }
         }
      }

      // N개 집 모두 이동 가능
      for (int r = 0; r < 31; r++) {
         for (int c = 0; c < 31; c++) {
            if (map[r][c] == N) {
               int dist = 0;
               for (Point home : homeList) {
                  dist += calc(r, c, home.r, home.c);
               }
               answer = Math.min(answer, dist);
            }
         }
      }

   }

   static int calc(int r1, int c1, int r2, int c2) {
      return Math.abs(r1 - r2) + Math.abs(c1 - c2);
   }

   static boolean checkRange(int r, int c) {
      return (r >= 0) && (r <= 30) && (c >= 0) && (c <= 30);
   }
}
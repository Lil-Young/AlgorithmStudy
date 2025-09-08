import java.util.*;
import java.io.*;

/*
 * 1. 먼저, 종수가 아두이노를 8가지 방향으로 이동시키거나, 그 위치에 그대로 놔둔다.
 * 2. 종수의 아두이노가 미친 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되며, 종수는 게임을 지게 된다.
 * 3. 미친 아두이노는 8가지 방향 중에서 종수의 아두이노와 가장 가까워지는 방향으로 한 칸 이동한다.
 * 	즉 종수의 위치를 (r1, s1), 미친 아두이노의 위치를 (r2, s2) 라고 했을 때, |r1-r2| + |s1-s2| 가 가장 작아지는 방향으로 이동한다.
 * 4. 미친 아두이노가 종수의 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되고, 종수는 게임을 지게 된다.
 * 5. 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴된다.
 * 
 * 종수의 시작 위치, 미친 아두이노의 위치, 종수가 움직이려고 하는 방향이 주어진다. 입력으로 주어진 방향대로 종수가 움직였을 때,
 * 	보드의 상태를 구하는 프로그램을 작성하시오. 중간에 게임에서 지게된 경우에는 몇 번째 움직임에서 죽는지를 구한다.
 * 
 * '.' 는 빈 칸
 * 'R'은 미친 아두이노
 * 'I'는 종수의 위치
 * 마지막 줄에는 길이가 100을 넘지않는 문자열이 주어진다.
 */


public class Main {
	static int R, C;
	static int jr, jc;
	static String s;
	static char[][] arr;
	static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	static List<Point> list;
	static int cnt;
	static boolean isEnd;
	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		list = new ArrayList<Point>();
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = str.charAt(j);
				if(arr[i][j] == 'I') {
					jr = i;
					jc = j;
				}
				if(arr[i][j] == 'R') {
					list.add(new Point(i, j));
				}
			}
		}
		s = br.readLine();
		
		cnt = 0;
		isEnd = false;
		L:for(int d=0; d<s.length(); d++) {
			int move = s.charAt(d) - '0';
			
			// 1. 종수의 아두이노가 8가지 방향으로 이동시키거나, 그 위치에 그대로 둔다.
			jr += dr[move];
			jc += dc[move];
			cnt++;
			// 2. 종수의 아두이노가 미친 아두이노가 있는 칸으로 이동한 경우 게임이 끝난다.
			for(int i=0; i<list.size(); i++) {
				Point p = list.get(i);
				if(jr==p.r && jc==p.c) {
					isEnd = true;
					break L; 
				}
			}
			// 3. 미친 아두이노는 8가지 방향 중에서 종수의 아두이노와 가장 가까워 지는 방향으로 한 칸 이동한다.
			for (int i = 0; i < list.size(); i++) {
				Point p = list.get(i);
				int bestDist = Integer.MAX_VALUE;
				int bestD = 0;
				for (int b = 1; b <= 9; b++) {
					if(b==5) continue;
					int r = p.r + dr[b];
					int c = p.c + dc[b];
					int dist = Math.abs(jr - r) + Math.abs(jc - c);
					if(dist < bestDist) {
						bestDist = dist;
						bestD = b;
					}
				}
				p.r+=dr[bestD];
				p.c+=dc[bestD];
			}
			
			// 4. 미친 아두이노가 종수의 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝난다.
			for (int i = 0; i < list.size(); i++) {
				Point p = list.get(i);
				if(p.r==jr && p.c==jc) {
					isEnd = true;
					break L; 
				}
			}
			
			// 5. 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴된다.
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < list.size(); i++) {
				Point p = list.get(i);
				map.put(p.r + "," + p.c, map.getOrDefault(p.r + "," + p.c, 0) + 1);
			}
			for(Map.Entry<String, Integer> entry : map.entrySet()) {
				if(entry.getValue() >= 2) {
					String[] f = entry.getKey().split(",");
					int f_r = Integer.parseInt(f[0]);
					int f_c = Integer.parseInt(f[1]);
					
					for (int i = 0; i < list.size(); i++) {
						Point p = list.get(i);
						if(p.r==f_r && p.c==f_c) {
							list.remove(i--);
						}
					}
				}
			}
		}
		if(isEnd) {
			System.out.println("kraj " + cnt);
		}else {
			char[][] result = new char[R][C];
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					result[i][j] = '.';
				}
			}
			
			
			result[jr][jc] = 'I';
			for (int i = 0; i < list.size(); i++) {
				Point p = list.get(i);
				result[p.r][p.c] = 'R'; 
			}
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print(result[i][j]);
				}
				System.out.println();
			}
		}
		
	}
}

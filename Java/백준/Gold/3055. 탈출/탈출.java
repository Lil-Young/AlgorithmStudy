import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 고슴도치는 비버의 굴로 "가능한 빨리" 도망가 홍수를 피할려고 한다.
 * '.' : 비어있는 곳, '*' : 물이 차있는 지역, 'X' : 돌
 * 'D' : 비버 위치, 'S' : 고슴도치 위치
 * 
 * 물도 매 분마다 비어있는 칸으로 확장, 물이 있는 칸과 인접해있는 비어있는 칸은 물이 차게 된다.
 * 물과 고슴도치는 돌을 통과할 수 없다.
 * 고슴도치는 물로 차있는 구역으로 이동 X
 * 물도 비버 소굴로 이동 X
 */

public class Main {
	static int R, C, result, dochiR, dochiC;
	static Character[][] arr;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new Character[R][C];
		v = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = s.charAt(j);
				if(arr[i][j] == 'S') {
					dochiR = i;
					dochiC = j;
				}
			}
		}
//		for (int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		result = 0;
		bfs(dochiR, dochiC);
		if(result == 0) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
//		for (int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
	}

	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
	
	static List<Integer> listR = new ArrayList<Integer>();
	static List<Integer> listC = new ArrayList<Integer>();
	
	private static void bfs(int r, int c) {
		// TODO Auto-generated method stub
		v[r][c] = true;
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c, 0));
		
		L:while(!queue.isEmpty()) {
			int qSize = queue.size();
			while (qSize-- > 0) {
				Point p = queue.poll();
//				System.out.println(arr[p.r][p.c]);
				
				if(arr[p.r][p.c]=='*') continue;
				// 현재 고슴도치 위치에서 4방 이동
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					
					if(nr<0 || nr>=R || nc<0 || nc>=C || v[nr][nc]) continue;
					
					if(arr[nr][nc] == '*' || arr[nr][nc] == 'X') continue;
					queue.offer(new Point(nr, nc, p.cnt + 1));
					v[nr][nc] = true;
					if(arr[nr][nc]=='D') {
						result = p.cnt+1;
						break L;
					}
					arr[nr][nc] = 'S';
				}
			}
			
			// 물이 있는 곳 4방 이동
			listR.clear();
			listC.clear();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(arr[i][j] == '*') {
						
						for (int d = 0; d < 4; d++) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							
							if(nr>=0 && nr<R && nc>=0 && nc<C && arr[nr][nc]=='.' | arr[nr][nc]=='S') {
								listR.add(nr);
								listC.add(nc);
							}
						}
						
					}
				}
			}
			
			for (int i = 0; i < listR.size(); i++) {
				int a = listR.get(i);
				int b = listC.get(i);
				arr[a][b] = '*';
			}
			
//			System.out.println("---------------------------------");
//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			
			
		}
		
		
	}

}

import java.util.*;
import java.io.*;

public class Main {
	static int T, N, M, result;
	static int[][] arr;
	static boolean[][] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
//			System.out.println(N + " " + M + " " + K);
			// 배추 위치
			arr = new int[N][M];
			for(int a=0; a<K; a++) {
				st = new StringTokenizer(br.readLine());
				arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			
			// 지렁이 심기
			result = 0;
			v = new boolean[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(arr[i][j]==1 && !v[i][j]) {
						bfs(i, j);
						result++;
					}
				}
			}
			System.out.println(result);
		}
	}
	
	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	private static void bfs(int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		v[r][c] = true;
		
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc]==1 &&!v[nr][nc]) {
					queue.offer(new Point(nr, nc));
					v[nr][nc] = true;
				}
			}
		}
		
		
	}
}


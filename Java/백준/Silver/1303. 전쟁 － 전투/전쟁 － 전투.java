import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] arr;
	static boolean[][] v;
	static int N, M;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int[] teamNumber;
	
	static class Point{
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static void bfs(int r, int c) {
		char curColor = arr[r][c];
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(r, c));
		v[r][c] = true;
		
		int cnt = 1;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr>=0 && nr<M && nc>=0 && nc<N && !v[nr][nc] && arr[nr][nc]==curColor) {
					v[nr][nc] = true;
					cnt++;
					queue.offer(new Point(nr, nc));
				}
			}
		}
//		if(cnt!=1) cnt-=1;
		if(curColor == 'W') teamNumber[0]+= Math.pow(cnt, 2);
		if(curColor == 'B') teamNumber[1]+= Math.pow(cnt, 2);
		
	}
	
	public static void main(String[] args) throws Exception{
		/*
		 * 예제
		 * 81+49=130, 1+64=65
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[M][N];
		v = new boolean[M][N];
		teamNumber = new int[2];
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(!v[i][j]) {
					bfs(i, j);
				}
			}
		}
		int re1 = teamNumber[0];
		int re2 = teamNumber[1];
		System.out.println(re1 + " " + re2);
	}

}

//3 3
//WBB
//WWW
//WWB
import java.util.*;
import java.io.*;

public class Main {
	static int N, M, result;
	static int[][] arr;
	static boolean[][] v;
	static int[] dr = {0, 0, 1, -1, -1, 1, -1, 1};
	static int[] dc = {1, -1, 0, 0, -1, 1, 1, -1};
	
	// 1은 땅, 0은 바다
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break;
			arr = new int[N][M];
			// 섬, 바다 심기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			v = new boolean[N][M];
			result = 0;
			// dfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(arr[i][j] == 1 && !v[i][j]) {
						dfs(i, j);
						result++;
					}
				}
			}
			System.out.println(result);
		}
	}
	private static void dfs(int r, int c) {
		
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc]==1 && !v[nr][nc]) {
				v[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}

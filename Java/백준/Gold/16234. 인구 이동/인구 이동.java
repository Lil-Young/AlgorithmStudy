import java.util.*;
import java.io.*;

public class Main {
    static int N, L, R;
    static int[][] arr;
    static boolean[][] v;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    
    static int sum, count;
    static List<int[]> union;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int day = 0;
		while(true) {
			v = new boolean[N][N];
			boolean moved = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(v[i][j]) continue;
					
					union = new ArrayList<>();
					sum=0;
					count=0;
					
					dfs(i, j);
					
					if(union.size() >= 2) {
						int avg = sum / count;
						for(int[] a : union) {
							arr[a[0]][a[1]] = avg;
						}
						moved = true;
					}
				}
			}
			
			if(!moved) {
				break;
			}
			day++;
		}
		System.out.println(day);
	}
	private static void dfs(int r, int c) {
		v[r][c] = true;
		union.add(new int[] {r, c});
		sum+=arr[r][c];
		count++;
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]) continue;
			
			int gap = Math.abs(arr[r][c] - arr[nr][nc]);
			if(gap >= L && gap <= R) {
				dfs(nr, nc);
			}
		}
	}
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static char[][] arr;
	static boolean[][] v;
	static int N;
	static int result;
	// 팔방
	static int[] dr = {0, 0, 1, -1, 1, 1, -1, -1};
	static int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};
	
	static void check(int r, int c) {
		int cnt = 0;
		for (int d = 0; d < 8; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0&&nr<N&&nc>=0&&nc<N&&arr[nr][nc]=='*') {
				cnt++;
			}
		}
		arr[r][c] = (char) (cnt+'0');
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new char[N][N];
			result=0;
			int dotCount = 0;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					arr[i][j] = str.charAt(j);
					if(arr[i][j]=='.') dotCount++;
				}
			}
			if(dotCount==N*N) {
				System.out.println(1);
				continue;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j]=='.') {
						check(i, j);
						
					}
				}
			}
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j]=='0') {
//						System.out.println(i + " " + j);
						arr[i][j]='t';
						dfs(i, j);
						result++;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j]!='t' && arr[i][j]!='*') {
						result++;
					}
				}
			}
			
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			
			
			System.out.println("#"+t+" "+result);
		}
	}

	private static void dfs(int r, int c) {
		arr[r][c]='t';
		v[r][c]=true;
		
		
		
		for (int d = 0; d < 8; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr>=0 && nr<N && nc>=0 && nc<N && !v[nr][nc]) {
				if(arr[nr][nc]!='0' && arr[nr][nc]!='*') {
					arr[nr][nc]='t';
					v[nr][nc]=true;
					continue;
				}
				if(arr[nr][nc]=='0') {
					dfs(nr, nc);
				}
			}
		}
	}


	
}

//1
//5
//..*..
//..*..
//.*..*
//.*...
//.*...

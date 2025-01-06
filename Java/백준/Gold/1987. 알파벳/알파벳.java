import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M, result;
	static Character[][] arr;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int[][] dist;
	static List<Character> list;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Character[N][M];
		result = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		dist = new int[N][M];
		list = new ArrayList<Character>();
		v = new boolean[N][M];
		v[0][0] = true;
		list.add(arr[0][0]);
		dfs(0, 0);

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(dist[i]));
//		}
		System.out.println(result+1);
	}

	private static void dfs(int r, int c) {
		// TODO Auto-generated method stub
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc] && !list.contains(arr[nr][nc])) {
				v[nr][nc] = true;
				dist[nr][nc] = dist[r][c] + 1;
				list.add(arr[nr][nc]);
				result = Math.max(result, dist[nr][nc]);
				dfs(nr, nc);
				v[nr][nc] = false;
				list.remove(list.size()-1);
			}
		}
		
	}
}

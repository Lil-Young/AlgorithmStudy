import java.util.*;
import java.io.*;
public class Main {
	static int N, M, K;
	static char[][] arr;
	static Map<String, Integer> dp;
	static String target;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		for (int k = 0; k < K; k++) {
			target = br.readLine();
			dp = new HashMap<>();
			int result = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == target.charAt(0)) {
						result += dfs(i, j, 0);
					}				}
			}
//			System.out.println(dp);
			System.out.println(result);
		}
	}
	static int[] dr = {0, 0, 1, -1, 1, 1, -1, -1};
	static int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};
	private static int dfs(int r, int c, int depth) {
		if (depth == target.length() - 1) return 1;

		String key = r + "," + c + "," + depth;
		if (dp.containsKey(key)) return dp.get(key);
		
		int count = 0;
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr==-1) nr=N-1;
			if(nr==N) nr=0;
			if(nc==-1) nc=M-1;
			if(nc==M) nc=0;;

			if (arr[nr][nc] == target.charAt(depth+1)) {
				count += dfs(nr, nc, depth+1);
			}
		}
		dp.put(key, count);
		return count;
	}
}

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T, up_r, up_c, down_r, down_c;
	static int[][] arr;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == -1) {
					if(cnt==0) {
						up_r = i;
						up_c = j;
						cnt++;
					}else {
						down_r = i;
						down_c = j;
					}
				}
			}
		}
		
		
		for (int t = 0; t < T; t++) {
			// 미세먼지 확산
			spread();
			// 공기청정기 ON
			clean();
		}
		// 결과
		System.out.println(results());
	}
	static Map<String, Integer> maps;
	private static void spread() {
		maps = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] > 0) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc] != -1) {
							String key = nr + ", " + nc;
							Integer value = arr[i][j] / 5;
							maps.put(key, maps.getOrDefault(key, 0) + value);
							cnt++;
						}
					}
					arr[i][j] -= (arr[i][j]/5)*cnt;
				}
			}
		}
		
		// maps에 있는 미세먼지 값 업데이트 하기
		for(Map.Entry<String, Integer> entry : maps.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			String[] idxs = key.split(", ");
			int r = Integer.parseInt(idxs[0]);
			int c = Integer.parseInt(idxs[1]);
			arr[r][c] += value;
		}
	}
	
	private static void clean() {
		// 위 공기청정기 move
		for (int i = up_r; i > 0; i--) {
			arr[i][0] = arr[i-1][0];
		}
		for (int i = up_c; i < M-1; i++) {
			arr[0][i] = arr[0][i+1];
		}
		for (int i = 0; i < up_r; i++) {
			arr[i][M-1] = arr[i+1][M-1];
		}
		for (int i = M-1; i > 0; i--) {
			arr[up_r][i] = arr[up_r][i-1];
		}
		arr[up_r][1] = 0;
		
		
		// 아래 공기청정기 move
		for (int i = down_r; i < N-1; i++) {
			arr[i][0] = arr[i+1][0];
		}
		for (int i = down_c; i < M-1; i++) {
			arr[N-1][i] = arr[N-1][i+1];
		}
		for (int i = N-1; i > down_r; i--) {
			arr[i][M-1] = arr[i-1][M-1];
		}
		for (int i = M-1; i > 0; i--) {
			arr[down_r][i] = arr[down_r][i-1];
		}
		arr[down_r][1] = 0;
		
		arr[up_r][up_c] = -1;
		arr[down_r][down_c] = -1;
	}
	
	private static int results() {
		int sum = 0;
		for(int[] a : arr) {
			for(int b : a) {
				if(b > 0) sum += b;
			}
		}
		return sum;
	}
}

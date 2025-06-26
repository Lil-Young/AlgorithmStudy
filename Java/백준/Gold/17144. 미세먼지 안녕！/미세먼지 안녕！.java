import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

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
		
		// (1, 1) 부터 시작임
		
		// 1. 미세먼지 확산
		// 공기청정기는 항상 1번 열에 설치되어있고, 크기는 두 행을 차지한다.
		// 미세먼지는 인접한 네바향으로 확산된다.
		// 인접한 방향에 공기청정기가 있거나 칸이 없으면 확산이 일어나지 않는다.
		// 확산되는 양은 A(r,c)/5 이고 소수점은 버린다.
		// (r,c)에 남은 미세먼지 양은 A(r, c) - A(r,c)/5*확산된 방향의 개수 이다.
		
		// 2. 공기청정기 작동
		// 공기청정기에서 바람이 나온다.
		// 위쪽 공기청정기의 바람은 반시계방향으로 순회하고, 아래쪽 공기청정기의 바람은 시계방향으로 순회한다.
		// 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
		// 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
		
		// T초가 지난 후 방에 남아있는 미세먼지 양을 구해보자.
		
		for (int t = 0; t < T; t++) {
			// 미세먼지 확산
			spread();
			// 공기청정기 작동
			work();
		}
		// 남아있는 미세먼지 합
		System.out.println(result());
	}
	static Map<String, Integer> map;
	private static void spread() {
		map = new HashMap<String, Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 0 이상이면
				if(arr[i][j] > 0) {
					int cnt = 0;
					// 사방탐색으로 확산되는 곳 구하기
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc] != -1) {
							String key = nr+ ", " + nc;
							map.put(key, map.getOrDefault(key,  0) + arr[i][j]/5);
							cnt++;
						}
					}
					// arr[i][j] - arr[i][j]/5*cnt 적용하기
					arr[i][j] -= (arr[i][j]/5)*cnt;
				}
			}
		}
		// 끝났으면 map에 있는거 arr에 적용하기
//		for (int i = 0; i < map.size(); i++) {
//			System.out.println(map.keySet());
//		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Integer value = entry.getValue();
		    
//		    System.out.println("key: " + key + ", value: " + value);
		    String[] keys = key.split(", ");
		    int r = Integer.parseInt(keys[0]);
		    int c = Integer.parseInt(keys[1]);
		    
		    arr[r][c] += value;
		}
	}
	private static void work() {
		// 위 공기청정기 반시계 방향으로 이동
		for (int i = up_r; i > 0; i--) {
			arr[i][0] = arr[i-1][0];
		}
		for (int i = 0; i < M-1; i++) {
			arr[0][i] = arr[0][i+1];
		}
		for (int i = 0; i < up_r; i++) {
			arr[i][M-1] = arr[i+1][M-1];
		}
		for (int i = M-1; i > 1; i--) {
			arr[up_r][i] = arr[up_r][i-1];
		}
		arr[up_r][1] = 0;
		
		// 아래 공기청정기 시계 방향으로 이동
		for (int i = down_r; i < N-1; i++) {
			arr[i][0] = arr[i+1][0];
		}
		for (int i = 0; i < M - 1; i++) {
			arr[N-1][i] = arr[N-1][i+1];
		}
		for (int i = N - 1; i > down_r; i--) {
			arr[i][M - 1] = arr[i-1][M-1];
		}
		for (int i = M - 1; i > 1; i--) {
			arr[down_r][i] = arr[down_r][i-1]; 
		}
		arr[down_r][1] = 0;
		
	    arr[up_r][up_c] = -1;
	    arr[down_r][down_c] = -1;
	}
	private static int result() {
		int sum = 2;
		for(int[] a : arr) {
			for(int b  : a) {
				sum+=b;
			}
		}
		return sum;
	}
}

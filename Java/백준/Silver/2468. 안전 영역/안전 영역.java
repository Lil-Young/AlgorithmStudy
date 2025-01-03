import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 어떤 지역의 높이 정보를 파악
	 * 그 지역에 많은 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대로 몇개가 만들어지는지 조사
	 * 장마철에 내리는 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠긴다고 가정
	 * 
	 */
	static int N, minHeight, maxHeight, result, areaCount;
	static int[][] arr;
	static boolean[][] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		minHeight = Integer.MAX_VALUE;
		maxHeight = Integer.MIN_VALUE;
		result = 0;
		N = Integer.parseInt(br.readLine().trim());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				minHeight = Math.min(minHeight, arr[i][j]);
				maxHeight = Math.max(maxHeight, arr[i][j]);
			}
		}
		// 아무 지역도 물에 잠기지 않을 수 있다.
		minHeight-=1;
		maxHeight+=1;
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//		System.out.println(minHeight + ", " + maxHeight);
		
		// minHeight ~ maxHeight
		// 0보다 작은 곳 전부 true 처리
		// dfs로 지역 갯수 찾기
		// 1보다 작은 곳 전부 true 처리
		// dfs로 지역 갯수 찾기
		for (int w = minHeight; w < maxHeight; w++) {
			areaCount = 0;
			// 물 높이(w)에 따라 방문배열 초기화
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j] <= w) {
						v[i][j] = true;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!v[i][j]) {
						areaCount++;
						dfs(i, j);
					}
				}
			}
			
			result = Math.max(result, areaCount);
			
		}
		System.out.println(result);
	}

	private static void dfs(int r, int c) {
		// TODO Auto-generated method stub
		v[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr>=0 && nr<N && nc>=0 && nc<N && !v[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
}

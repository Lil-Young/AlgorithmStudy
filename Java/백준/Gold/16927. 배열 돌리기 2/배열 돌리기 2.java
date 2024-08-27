import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R, startIdx;
	static int[][] arr;
	
	static void rotate(int startIdx) {
		int n = N-startIdx;
		int m = M-startIdx;
		int r = startIdx;
		int c = startIdx;
		int range = 2*(N-2*startIdx)+2*(M-2*startIdx-2);
		int count = R % range;
		for (int k = 0; k < count; k++) {
			// 처음 시작하는 위치 값 저장
			int temp = arr[r][c];
			// 반복 횟수 계산
			
			// 왼쪽으로 이동
			
			for (int i = c; i < m-1; i++) {
				// 0 1, 1 2, 2 3
				arr[r][i] = arr[r][i+1];
			}
			// 위로 이동
//			c = m-1;
			for (int i = r; i < n-1; i++) {
				// 0 3, 1 3, 2 3, 3 3
				arr[i][m-1] = arr[i+1][m-1];
			}
			// 오른쪽으로 이동
			for (int i = m-1; i > c; i--) {
				// 3 3, 3 2, 3 1, 3 0
				arr[n-1][i] = arr[n-1][i-1];
			}
			// 아래로 이동
			for (int i = n-1; i > r; i--) {
				// 3 0, 2 0, 1 0, 0 0
				arr[i][c] = arr[i-1][c];
			}
			// 시작 위치 아래에 temp 넣기
			arr[r+1][c] = temp;
			
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		// 1. 첫 배열을 만든다.
		// 2. 회전해야 되는 처음 인덱스를 구하기 위해 N과 M 중 최솟값을 구하고 나누기 2 를 한다.
		// 3. R 만큼 배열을 돌리는데, 원위치가 되는 반복횟수 만큼 나눠서 나머지를 구한다.
		// 4. 나머지만큼 배열을 돌린다.
		//	4-1) 배열을 돌릴 때, 각 껍질마다 반복하는 횟수가 다르다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		startIdx = Math.min(N, M)/2;
//		System.out.println(startIdx);
		for (int i = 0; i < startIdx; i++) {
			rotate(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}

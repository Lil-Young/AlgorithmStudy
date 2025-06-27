import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, d, s;
	static int[][] arr;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static List<int[]> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다.
		list = new ArrayList<int[]>();
		list.add(new int[]{N-1, 0});
		list.add(new int[]{N-1, 1});
		list.add(new int[]{N-2, 0});
		list.add(new int[]{N-2, 1});
		// M번 반복한다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken())-1; // 방향
			s = Integer.parseInt(st.nextToken()); // 거리
			
			// 구름 이동 및 비 추가
			move();
			
			// 구름 생성
			make();
		}
		// 물의 양의 합을 구한다.
		System.out.println(result());
	}
	
	static int[] dr2 = {1, 1, -1, -1};
	static int[] dc2 = {1, -1, 1, -1};
	private static void move() {
		for (int i = 0; i < list.size(); i++) {
			int[] Idx = list.get(i);
			for (int j = 0; j < s; j++) {
				Idx[0] += dr[d];
				Idx[1] += dc[d];
				if(Idx[0] == -1) Idx[0] = N-1;
				if(Idx[0] == N) Idx[0] = 0;
				if(Idx[1] == -1) Idx[1] = N-1;
				if(Idx[1] == N) Idx[1] = 0;				
			}
			arr[Idx[0]][Idx[1]]+=1;
		}
		
		// 비 추가
		for(int[] Idx : list) {
			int sum = 0;
			for (int d = 0; d < 4; d++) {
				int nr = Idx[0] + dr2[d];
				int nc = Idx[1] + dc2[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N && arr[nr][nc] > 0) sum++;
			}
			arr[Idx[0]][Idx[1]] += sum;
		}
	}
	
	private static void make() {
		List<String> temp = new ArrayList<String>();
		for(int[] Idx : list) {
			int r = Idx[0];
			int c = Idx[1];
			temp.add(r + ", " + c);
		}
		
		// 기존에 있던 구름 삭제
		list.clear();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j] >= 2 && !temp.contains(i + ", " + j)) {
					arr[i][j] -= 2;
					list.add(new int[]{i, j});
				}
			}
		}
	}
	
	private static int result() {
		int sum = 0;
		for(int[] a : arr) {
			for(int b : a) sum+=b;
		}
		return sum;
	}
}
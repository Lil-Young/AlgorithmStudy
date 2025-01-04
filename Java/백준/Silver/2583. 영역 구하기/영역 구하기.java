import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//5 7 3
//0 2 4 4
//1 1 2 5
//4 0 6 2

//3
//1 7 13
public class Main {
	
	/*
	 * K개의 줄에는 한 줄에 하나씩
	 * 직사각형의 왼쪽 아래 꼭짓점의 x, y 좌표값과 
	 * 오른쪽 위 꼭짓점의 x,y 좌표값이 빈칸을 사이에 두고 차례로 주어진다.
	 * 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위는 (N, M)이다.
	 */
	static int N, M, K, result;
	static boolean[][] v;
	static List<Integer> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		v = new boolean[N][M];
		int s_x, s_y, f_x, f_y;
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			f_x = x2-x1;
			f_y = y2-y1;
			
			s_x = N-x1;
			s_y = y1;
			
			for (int i = 0; i < f_x; i++) {
				s_x--;
				s_y = y1;
				for (int j = 0; j < f_y; j++) {
					v[s_x][s_y] = true;
					s_y++;
				}
			}
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(v[i]));
//			}
//			System.out.println("-----------");
		}
		list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!v[i][j]) {
					result = 0;
					dfs(i, j);
					list.add(result);
				}
			}
		}
		Collections.sort(list);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	private static void dfs(int r, int c) {
		// TODO Auto-generated method stub
		result++;
		v[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
}

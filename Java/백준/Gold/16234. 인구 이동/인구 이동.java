import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R;
	static boolean isMove;
	static int[][] arr;
	static boolean[][] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static List<Point> list;
	
	static class Point{
		int r, c;
		Point(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		while(true) {
			isMove = false;
			
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!v[i][j]) {
						boolean isPossible = check(i, j);
						if(!isPossible) continue;
						isMove = true;
						list = new ArrayList<>();
						// list에 공유하는 좌표를 넣는다.
						bfs(i, j);
						
						// list에 있는 값을 전부 더해서 평균을 만든다.
						int sum = 0;
						for(Point p : list) {
							sum+=arr[p.r][p.c];
						}
						int avg = sum/list.size();
						// list에 있는 값을 평균으로 변경한다.
						for(Point p : list) {
							arr[p.r][p.c] = avg;
						}
					}
				}
			}
			if(!isMove) break;
			result++;
		}
		System.out.println(result);
		
	}
	private static boolean check(int r, int c) {
		// 먼저 사방에 L이상 R이하인 좌표가 있는지 확인한다.
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
			int gap = Math.abs(arr[r][c] - arr[nr][nc]);
			if(gap>=L && gap<=R) {
				return true;
			}
		}
		return false;
	}
	
	private static void bfs(int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		v[r][c] = true;
		list.add(new Point(r, c));
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=N || v[nr][nc]) continue;
				int gap = Math.abs(arr[p.r][p.c] - arr[nr][nc]);
				if(gap>=L && gap<=R) {
					queue.offer(new Point(nr, nc));
					v[nr][nc] = true;
					list.add(new Point(nr, nc));
				}
			}
		}
	}
}
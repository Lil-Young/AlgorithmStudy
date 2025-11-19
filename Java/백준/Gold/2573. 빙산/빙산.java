import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] arr;
	static boolean[][] v;
	static List<Point> iceList;
	static int year, iceCount;
	static boolean isDivide;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int[][] zeroNumber;
	
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) throws Exception {
		// 빙산 1, 바다 0
		// 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다. (0보다 더 줄어들지는 않는다.)
		// 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오. (다 녹을 때까지 두 덩어리 이상 분리되지 않으면 0 출력)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		iceList = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] > 0) iceList.add(new Point(i, j));
			}
		}
		
		year = 0;
		isDivide = false;
		zeroNumber = new int[N][M];
		while(!isDivide && !iceList.isEmpty()) {
			// 각 빙산 위치마다 0의 개수를 센다
			for(Point p : iceList) {
				int cnt = zeroCount(p);
				zeroNumber[p.x][p.y] = cnt; 
			}

			// 각 빙산을 녹인다.
			for(int i=0; i<iceList.size(); i++) {
				Point p = iceList.get(i);
				if(arr[p.x][p.y] - zeroNumber[p.x][p.y] <= 0) {
					arr[p.x][p.y] = 0;
					iceList.remove(i--);
				}else {
					arr[p.x][p.y] -= zeroNumber[p.x][p.y]; 
				}
				zeroNumber[p.x][p.y] = 0;
			}

			// 빙산 덩어리 갯수를 센다.
			iceCount = 0;
			v = new boolean[N][M];
			for(Point p : iceList) {
				if(!v[p.x][p.y]) {
					dfs(p.x, p.y);
					iceCount++;
				}
			}
			
			if(iceCount >= 2) isDivide = true;
			year++;
		}
		
		if(iceList.isEmpty()) {
			System.out.println(0); return;
		}
		System.out.println(year);
		
	}
	
	private static int zeroCount(Point p) {
		int num = 0;
		for(int d=0; d<4; d++) {
			int nr = p.x + dr[d];
			int nc = p.y + dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
			if(arr[nr][nc] == 0) num++;
		}
		
		return num;
	}
	
	private static void dfs(int r, int c) {
		v[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc] || arr[nr][nc] <= 0) continue;
			dfs(nr, nc);
		}
	}
	
}




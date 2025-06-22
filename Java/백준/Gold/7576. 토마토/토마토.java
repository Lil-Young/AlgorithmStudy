import java.util.*;
import java.io.*;

public class Main {
	static int N, M, day;
	static int[][] arr;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static boolean[][] v;
	static List<int[]> index;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		index = new ArrayList<int[]>();
		int countZero = 0;
		int countOne = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) countZero++;
				if(arr[i][j] == 1) {
					int[] temp = {i, j};
					index.add(temp);
					countOne++;
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		
		// 안익은것이 없으면 모두가 익었다는 소리 -> 0 출력
		if(countZero == 0) {
			System.out.println(0);
			return;
		}
		
		// 토마토가 모두 익지는 못하는 상황이면 -1을 출력, 1이 아예 없든가, -1로 가려져 익지 못하든가
		if(countOne == 0) {
			System.out.println(-1);
			return;
		}
		
		// 1은 익은거, 0은 안익은거, -1은 토마토가 없는거
		v = new boolean[N][M];
		day = 0;
		bfs();
		boolean inZero = checkZero();
		if(inZero) {
			System.out.println(-1);
		}else {
			System.out.println(day-1);	
		}
	}
	
	private static boolean checkZero() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==0) return true;
			}
		}
		return false;
	}
	
	
	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	private static void bfs() {
		// 익은거를 queue에 넣기
		Queue<Point> queue = new ArrayDeque<>();
		for(int l=0; l<index.size(); l++) {
			int[] arr = index.get(l);
			queue.offer(new Point(arr[0], arr[1]));
			v[arr[0]][arr[1]]=true;
		}
		
		while(!queue.isEmpty()) {
			int QSize = queue.size();
			day++;
			for(int size=0; size<QSize; size++) {
				Point p = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					
					if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc]==0 && !v[nr][nc]) {
						queue.offer(new Point(nr, nc));
						v[nr][nc] = true;
						arr[nr][nc] = 1;
					}
				}
			}
		}
	}
}

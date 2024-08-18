import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static boolean[][] v;
	static int N, M, maxNumber;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	static class Point{
		int r, c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static void bfs(int r, int c) {
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(r, c));
		v[r][c] = true;
		//1 0 0 0
		//0 0 0 0
		//0 1 0 0
		int count = 1;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			maxNumber = Math.max(maxNumber, count);
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && !v[nr][nc] && arr[nr][nc]==1) {
					v[nr][nc] = true;
					count+=1;
					queue.offer(new Point(nr, nc));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		v = new boolean[N][M];
		maxNumber = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = 0;
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x-1][y-1] = 1;
		}
		
//		for(int[] a : arr) {
//			for(int b : a) {
//				System.out.print(b + " ");
//			}
//			System.out.println();
//		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!v[i][j] && arr[i][j]==1) {
					bfs(i, j);
				}
			}
		}
		System.out.print(maxNumber);
	}

}

//1 0 0 0
//0 1 1 0
//1 1 0 0

//3 4 2
//1 1
//3 2

//1 0 0 0
//0 0 0 0
//0 1 0 0


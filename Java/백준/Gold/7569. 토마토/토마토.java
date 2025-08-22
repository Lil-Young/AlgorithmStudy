import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int h, r, c;
	Point(int h, int r, int c){
		this.h = h;
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int N, M, H;
	static int result = 0, cnt = 0;
	static int[][][] arr;
	static boolean[][][] v;
	static int[] dr = {-1, 1, 0, 0, 0, 0};
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, -1, 1};
	static 	Queue<Point> queue = new ArrayDeque<Point>();

	private static void bfs() {
		while(!queue.isEmpty()) {			
			Point p = queue.poll();
			for(int l = 0; l < 6; l++) {
				int nh = p.h + dh[l];
				int nr = p.r + dr[l];
				int nc = p.c + dc[l];
				
				if(nh>=0 && nh<H && nr>=0 && nr<N && nc>=0 && nc<M && (arr[nh][nr][nc]==0)) {
					queue.offer(new Point(nh, nr, nc));
					arr[nh][nr][nc] =  arr[p.h][p.r][p.c]+1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][N][M];
		v = new boolean[H][N][M];
		boolean TomatoRipe = false;
		for(int i = 0; i < H; i++){
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if(arr[i][j][k] == 0) {
						TomatoRipe = true;
					}
				}
			}
		}
		
		// 토마토가 다 익은 상태
		if(!TomatoRipe) {
			System.out.println(0);
			return;
		}
		

		for(int i = 0; i < H; i++){
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(arr[i][j][k]==1) {
						queue.offer(new Point(i, j, k));
					}
				}
			}
		}
		bfs();
		
		boolean NotTomato = false;
		L:for(int i = 0; i < H; i++){
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(arr[i][j][k] == 0) {
						NotTomato = true;
						break L;
					}
					result = Math.max(result, arr[i][j][k]);
				}
			}
		}
		
		if(NotTomato) {
			System.out.println(-1);
		}else {
			System.out.println((result-1));	
		}		
		
		
	}
}

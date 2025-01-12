import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 첫째 줄 N, M
 * 둘째 줄, 처음에 로봇 청소기가 있는 칸의 좌표 (r, c)와 처음 로봇 청소기 방향 d가 입력
 * 셋째 줄, NXM 배열 상태, 0 : 청소가 안된 칸, 1 : 벽
 */
public class Main {
	static int N, M, x, y, forward, result;
	static int[][] arr;
	// {0:북}, {1:동}, {2:남}, {3:서}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		st = new StringTokenizer(br.readLine().trim());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		forward = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/*
		 * 1. 현재 칸이 청소되지 않은 경우, 현재 칸을 청소한다.
		 * 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우,
		 * 	2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면, 한 칸 후진하고 1번으로 돌아간다.
		 * 	2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
		 * 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
		 * 	3-1. 반시계 방향으로 90도 회전한다.
		 * 	3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
		 * 	3-3. 1번으로 돌아간다.
		 */
		result = 0 ;
		bfs();
		System.out.println(result);
	}
	static class Point{
		int r, c, forward;

		public Point(int r, int c, int forward) {
			super();
			this.r = r;
			this.c = c;
			this.forward = forward;
		}
		
	}
	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
	}
	
	private static void bfs() {
		// TODO Auto-generated method stub
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y, forward));
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
//			System.out.println(p.r + " " + p.c + " " + p.forward + " " + arr[p.r][p.c]);
//			print();
			// 1. 현재 칸이 청소되지 않은 경우, 현재 칸을 청소한다.
				// 0:청소X, 1:벽, 2:청소완료
			if(arr[p.r][p.c]==0) {
				arr[p.r][p.c] = 2;
				result++;
			}
			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우,
			if(arr[p.r+dr[0]][p.c+dc[0]]!=0
					&& arr[p.r+dr[1]][p.c+dc[1]]!=0
					&& arr[p.r+dr[2]][p.c+dc[2]]!=0
					&& arr[p.r+dr[3]][p.c+dc[3]]!=0) {
				//	2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면, 한 칸 후진하고 1번으로 돌아간다.
				if(p.forward==0 && arr[p.r+dr[2]][p.c+dc[2]]!=1) {
					queue.offer(new Point(p.r+dr[2], p.c+dc[2], 0));
				} else if(p.forward==2 && arr[p.r+dr[0]][p.c+dc[0]]!=1) {
					queue.offer(new Point(p.r+dr[0], p.c+dc[0], 2));
				} else if(p.forward==1 && arr[p.r+dr[3]][p.c+dc[3]]!=1) {
					queue.offer(new Point(p.r+dr[3], p.c+dc[3], 1));
				} else if(p.forward==3 && arr[p.r+dr[1]][p.c+dc[1]]!=1) {
					queue.offer(new Point(p.r+dr[1], p.c+dc[1], 3));
				}
				// 	2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
				else {
					break;
				}
			}
			// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
			else {
				// 3-1. 반시계 방향으로 90도 회전한다.
				// 북(0) -> 서(3) -> 남(2) -> 동(1)
				int nd = 0;
				if(p.forward==0) nd = 3;
				if(p.forward==3) nd = 2;
				if(p.forward==2) nd = 1;
				if(p.forward==1) nd = 0;
//				System.out.println("nd: " + nd);
				//	3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
				if(arr[p.r+dr[nd]][p.c+dc[nd]]==0) {
					queue.offer(new Point(p.r+dr[nd], p.c+dc[nd], nd));
				}else {
					queue.offer(new Point(p.r, p.c, nd));
				}
				//	3-3. 1번으로 돌아간다.
			}
			
		}
	}
}

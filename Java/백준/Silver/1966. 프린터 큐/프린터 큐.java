import java.util.*;
import java.io.*;

public class Main {
	static int T, N, M;
	static Queue<Point> queue;
	static class Point{
		int idx, important;
		Point(int idx, int important){
			this.idx=idx;
			this.important=important;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 문서 개수
			M = Integer.parseInt(st.nextToken()); // Queue에 몇 번째 놓여있는지
			queue = new ArrayDeque<Point>();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int val = Integer.parseInt(st.nextToken());
				queue.offer(new Point(i, val));
			}
			
			int result = process();
			System.out.println(result);
		}
	}
	private static int process() {
		int cnt = 0;
		
		boolean find = false;
		
		while(true) {
			Point p = queue.poll();
			// queue 안에 현재 값 보다 큰 값이 있는지 확인
			boolean isMax = false;
			for(Point p2 : queue) {
				if(p.important < p2.important) {
					isMax = true;
				}
			}
			
			if(isMax) {
				queue.offer(p);
			}else if(!isMax && M==p.idx) {
				cnt++;
				break;
			}else {
				cnt++;
			}
		}		
		
		return cnt;
	}
}

import java.util.*;
import java.io.*;

public class Main {
	static int N, K, result;
	static int q, w;
	static int[][] arr;
	static List<Point> list;
	static class Point{
		int a, b, c, sum;
		Point(int a, int b, int c, int sum) {
			this.a=a;
			this.b=b;
			this.c=c;
			this.sum=sum;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		process();
		System.out.println(result);
	}
	private static void process() {
		result = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			q = arr[i][0];
			for (int j = 0; j < N; j++) {
				w = arr[j][1];
				
				PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
				
				for (int k = 0; k < N; k++) {
					if(arr[k][0] <= q && arr[k][1] <= w) {
						pq.offer(arr[k][2]);
						if(pq.size() > K) {
							pq.poll();
						}
					}
					if(pq.size() == K) {
						int min = pq.peek();
						result = Math.min(result, (q+w+min));
					}
				}
			}
		}
	}
}

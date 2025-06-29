import java.util.*;
import java.io.*; 

public class Main {
	static int N, M, count;
	static int[] targets;
	static Deque<Integer> dq;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dq = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) {
			dq.offer(i);
		}
		
		targets = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			targets[i] = Integer.parseInt(st.nextToken());
		}
		
		count = 0;
		for (int t = 0; t < M; t++) {
			int target = targets[t];
			// target 찾기
			int idx = 0;
			for(int val : dq) {
				if(val == target) break;
				idx++;
			}
			
			// 왼쪽 회전
			if(idx <= dq.size()/2) {
				for (int i = 0; i < idx; i++) {
					int val = dq.pollFirst();
					dq.offerLast(val);
					count++;
				}
			}
			// 오른쪽 회전
			else {
				for (int i = 0; i < dq.size() - idx; i++) {
					int val = dq.pollLast();
					dq.offerFirst(val);
					count++;
				}
			}
			
			dq.pollFirst();
		}
		System.out.println(count);
	}
}

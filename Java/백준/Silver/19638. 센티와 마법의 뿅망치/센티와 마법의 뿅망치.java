import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 뿅망치에 맞으면
	 * 뿅망치에 맞은 사람의 키/2 로 변함.
	 * 단, 키가 1인 경우 더 줄어들 수 없어 영향을 받지 않음
	 * 뿅망치 횟수 제한있어서 전략을 짯는데, 매번 가장 키가 큰 거인 가운데 하나를 때리는 것
	 * 과연 센티 전략을하면, 거인나라의 모든 거인이 센티보다 키가 작도록할 수 있냐?
	 * 
	 * 첫 째줄에는 센티를제외한 거인의 나라 인구수, 센티의 키, 뿅망치횟수제한
	 * 둘 째줄부터 N개의 줄에 각 거인의 키를 나타내는 정수 H가 주어진다.
	 */
	
	static int N, H, T;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 거인 수
		H = Integer.parseInt(st.nextToken()); // 센티 키
		T = Integer.parseInt(st.nextToken()); // 뿅망치횟수제한
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 0; i < N; i++) {
			// 거인 키
			pq.offer(Integer.parseInt(br.readLine()));
		}
		boolean isNo = true;
		for (int t = 1; t < T+1; t++) {
//			System.out.println(pq);
			int val = pq.poll();
			if(H > val) {
				System.out.println("YES");
				System.out.println(t-1);
				isNo = false;
				break;
			}else {
				if(val/2 < 1) {
					pq.offer(1);
				}else {
					pq.offer(val/2);	
				}
			}
		}
		if(isNo) {
			int val = pq.poll();
			if(H > val) {
				System.out.println("YES");
				System.out.println(T);
			}else {
				System.out.println("NO");
				System.out.println(val);	
			}
		}
	}
}

/*
 * 창구 하나인 은행, 대기줄에는 손님이 N명있다.
 * x번 손님에 대한 정보는 x번 손님의 id값인 Px와 업무를 처리하는데 필요한 시간인 tx초로 정보가 주어진다.
 * 영업 시작하고, 들어오는 손님은 M명 있다.
 * 이 손님들은 입력을 받은 순서대로 각각 N+1, N+2, ... N+M 번 손님이 된다.
 * 손님은 은행에 들어옴과 동시에, 대기 큐의 맨 뒤에 서게된다.
 * 손님들 정보는 x번 손님의 id 값인 Px와 업무처리필요시간 tx초, 영업 시작 cx초 후에 들어왔다는 정보
 * N+1번 손님이 은행을 영업을 시작하고 cN+1 초 후에 들어왔다고 생각해보자.
 */

import java.util.*;
import java.io.*;

public class Main {
	static int N, T, W, M;
	static Queue<Customer> q;
	static PriorityQueue<Customer> pq;
	static StringBuilder sb = new StringBuilder();
	static class Customer {
		int id, time, inTime;
		Customer(int id, int time, int inTime) {
			this.id = id;
			this.time = time;
			this.inTime = inTime;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 대기줄 손님, 1
		T = Integer.parseInt(st.nextToken()); // 최대업무시간, 5
		W = Integer.parseInt(st.nextToken()); // 7
		q = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			q.offer(new Customer(id, time, 0));
		}
		
		M = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>((o1, o2) -> o1.inTime - o2.inTime);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int inTime = Integer.parseInt(st.nextToken());
			pq.offer(new Customer(id, time, inTime));
		}
		round_robin();
		System.out.println(sb);
	}
	private static void round_robin() {
	    int current_time = 0;

	    while(current_time < W && !q.isEmpty()) {
	        Customer current = q.poll();

	        // 1. 손님 처리 시간 계산
	        int work = Math.min(current.time, T);

	        for(int i = 0; i < work && current_time < W; i++) {
	            sb.append(current.id).append("\n");
	            current_time++;

	            // 2. 그 사이에 들어온 사람 추가
	            while(!pq.isEmpty() && pq.peek().inTime == current_time) {
	                q.offer(pq.poll());
	            }
	        }

	        // 3. 일 다 못 한 경우 큐 뒤로
	        current.time -= work;
	        if(current.time > 0) {
	            q.offer(current);
	        }

	        // 4. 현재 사람 다 처리했는데도 들어온 손님 있는지 확인
	        while(!pq.isEmpty() && pq.peek().inTime <= current_time) {
	            q.offer(pq.poll());
	        }

	        // 5. 큐 비었으면, 새 손님 기다리기
	        if(q.isEmpty() && !pq.isEmpty()) {
	            current_time = pq.peek().inTime;
	            q.offer(pq.poll());
	        }
	    }
	}
}

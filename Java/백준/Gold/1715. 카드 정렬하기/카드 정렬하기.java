import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		int sum = 0;
		while(pq.size()>=2) {
			int val1 = pq.poll();
			int val2 = pq.poll();
			sum += (val1+val2);
			pq.offer(val1+val2);
		}
		System.out.println(sum);
	}
}


//10+20 = 30
//30+40 = 70

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		int N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if(a==0 && pq.size()==0) {
				System.out.println(-1);
			}else if(a==0 && pq.size()>0) {
				System.out.println(pq.poll());
			}else if(a>0) {
				for(int i=0; i<a; i++) {
					pq.offer(Integer.parseInt(st.nextToken()));
				}
			}
		}

	}
}


import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static Queue<Integer> queue;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		queue = new ArrayDeque<>();
		for(int i=1; i<N+1; i++) {
			queue.offer(i);
		}
		
		int cnt=1;
		int value=0;
		while(!queue.isEmpty()) {
			value=queue.poll();
			if(cnt++%2 == 0) queue.offer(value);
		}
		System.out.println(value);
	}
}

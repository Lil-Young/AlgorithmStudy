import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int time[];
	static int move [] = new int []{1, -1, 2};
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		time = new int[100_001];
		
		bfs();
		System.out.println(time[K]);
		System.out.println(cnt);
    }
	
	
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.offer(N);

		if (N==K) {
			cnt++;
			return;
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int i=0; i<3; i++) {
				int next;
				if (i==2) next = now * move[i];
				else next = now + move[i];
				
				if (next<0 || next>100_000 || (time[next] != 0 && time[next] < time[now]+1)) continue;
				time[next] = time[now]+1;
				queue.offer(next);
				
				if (next == K) cnt ++;
			}
		}
	}
}

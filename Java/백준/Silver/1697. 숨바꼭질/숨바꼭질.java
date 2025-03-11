import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int value, K, cnt, result;
	static boolean v[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		v = new boolean[100001];
		result = bfs(N);
		System.out.println(result);
		
	}

	private static int bfs(int n) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(n);
		v[n] = true;
		int depth = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				int value = queue.poll();
				if(value == K) return depth;
				
				int[] arr = {value-1, value*2, value+1};
				
				for(int nextValue : arr) {
					if(nextValue >= 0 && nextValue <= 100000 && !v[nextValue]) {
						queue.offer(nextValue);
						v[nextValue] = true;
					}
				}
			}
			depth++;
		}
		return 0;
	}

}

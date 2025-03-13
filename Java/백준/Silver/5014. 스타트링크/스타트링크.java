import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int F, S, G, U, D;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken()); // 가장 높은 층
		S = Integer.parseInt(st.nextToken()); // 사람 있는 층
		G = Integer.parseInt(st.nextToken()); // 스타트링크 있는 층
		U = Integer.parseInt(st.nextToken()); // 위
		D = Integer.parseInt(st.nextToken()); // 아래
		
		int result = bfs();
		if(result == -1) System.out.println("use the stairs");
		else System.out.println(result);
	}
	private static int bfs() {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(S);
		boolean[] v = new boolean[F+1];
		v[S] = true;
		v[0] = true;
		int depth = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int p = queue.poll();
				if(p == G) return depth;
				int[] arr = {p+U, p-D};
				for(int value : arr) {
					if(value>=1 && value<=F && !v[value]) {
						queue.offer(value);
						v[value] = true;
					}
				}
			}
			depth++;
		}
		return -1;
	}
}

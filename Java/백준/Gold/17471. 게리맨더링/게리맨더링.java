import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int V, totoalNumber, result;
	static int[][] adjMatrix;
	static boolean[] v;
	static HashMap<Integer, Integer> dict;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		dict = new HashMap<Integer, Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 각 번호의 인구 수 저장
		for (int i = 1; i < V+1; i++) {
			dict.put(i, Integer.parseInt(st.nextToken()));
			totoalNumber+=dict.get(i);
		}
		
		// 인접행렬과 방문배열 초기화
		adjMatrix = new int[V+1][V+1];
		v = new boolean[V+1];
		result = Integer.MAX_VALUE;
		
		// 인접행렬
		for (int i = 1; i < V+1; i++) {
			st = new StringTokenizer(br.readLine());
			int E = Integer.parseInt(st.nextToken());
			for (int j = 0; j < E; j++) {
				int num = Integer.parseInt(st.nextToken());
				adjMatrix[i][num] = 1;
				adjMatrix[num][i] = 1;
			}
		}
//		for (int i = 0; i < V+1; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}
		/*
		 * 1 / 2, 3, 4, 5, 6
		 * 1, 2, 3, 5, 6 / 4
		 * 1, 4 / 2, 3, 5, 6
		 * 1, 3, 4 / 2, 5, 6
		 */
		calc(1, 0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
		
	}
	static void calc(int idx, int sum) {
		// idx>V 이면 두 그룹이 잘 나눠져 있는지 확인
		if (idx > V) {
			if(isConnected(true) && isConnected(false)) {
				result = Math.min(result, Math.abs(sum - (totoalNumber - sum)));
			}
			return;
		}
		// 조합
		v[idx] = true;
		calc(idx+1, sum + dict.get(idx));
		v[idx] = false;
		calc(idx+1, sum);
	}
	private static boolean isConnected(boolean b) {
		// b가 true인 경우 / b가 false인 경우
		
		boolean visited[] = new boolean[V+1];
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		// visited 배열의 첫번째가 b와 같다면 queue에 인덱스를 넣고, true처리
		for (int i = 1; i < V+1; i++) {
			if(v[i] == b) {
				queue.offer(i);
				visited[i]=true;
				break;
			}
		}
		
		while(!queue.isEmpty()) {
			int q = queue.poll();
			for (int i = 1; i < V+1; i++) {
				// 1~V+1까지 v[i]가 b고, 인접행렬이 1이고, visited[i]가 false면 연결되어있음
				if(v[i]==b && adjMatrix[q][i]==1 && !visited[i]) {
					queue.offer(i);
					visited[i]=true;
				}
			}
		}
		
		// 같은 그룹인데 방문한 적이 없다면 연결되지 않음
		for (int i = 1; i < V+1; i++) {
			if(v[i]==b && !visited[i]) {
				return false;
			}
		}
		return true;
	}

}

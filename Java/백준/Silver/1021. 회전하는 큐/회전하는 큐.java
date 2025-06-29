import java.util.*;
import java.io.*;

// 뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치다.)
// 이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력
 

public class Main {
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        st = new StringTokenizer(br.readLine());
        int[] targets = new int[M];
        for (int i = 0; i < M; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }
		
		
        int count = 0;

        for (int i = 0; i < M; i++) {
            int target = targets[i];

            int idx = 0;
            for (int val : deque) {
                if (val == target) break;
                idx++;
            }

            if (idx <= deque.size() / 2) {
        		// 왼쪽으로 한 칸 이동시킨다.
                for (int j = 0; j < idx; j++) {
                    deque.addLast(deque.pollFirst());
                    count++;
                }
            } else {
        		// 오른쪽으로 한 칸 이동시킨다.
                for (int j = 0; j < deque.size() - idx; j++) {
                    deque.addFirst(deque.pollLast());
                    count++;
                }
            }

    		// 첫 번째 원소를 뽑아낸다.
            deque.pollFirst();
        }
        System.out.println(count);
	}
}
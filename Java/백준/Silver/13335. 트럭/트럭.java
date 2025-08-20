import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 하나의 차선으로 된 다리가 하나 있다.
 * 이 다리를 n개의 트럭이 건너가려고한다.
 * 트럭의 순서는 바꿀 수 없다.
 * 트럭의 무게는 서로 같지 않을 수 있다.
 * 다리 위에는 단지 w 대의 트럭만 동시에 올라갈 수 있다.
 * 다리의 길이는 w 단위 길이
 * 각 트럭들은 하나의 단위시간에 하나의 단위길이만큼만 이동할 수 있다고 가정
 * 다리 위에 올라가 있는 트럭들의 무게 합 <= 다리의 최대하중인 L
 * 
 */

public class Main {
	
	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> bridge = new LinkedList();
        for (int i = 0; i < w; i++) bridge.offer(0);
        int time = 0;
        int totalWeight = 0;
        int idx = 0;

        while (idx < n) {
            time++;
            totalWeight -= bridge.poll();

            if (totalWeight + trucks[idx] <= L) {
                bridge.offer(trucks[idx]);
                totalWeight += trucks[idx];
                idx++;
            } else {
                bridge.offer(0);
            }
        }

        System.out.println(time + w);
    }
}

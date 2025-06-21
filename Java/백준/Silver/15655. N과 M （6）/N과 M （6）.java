import java.util.*;
import java.io.*;

public class Main {
	// 걍 입력값을 받는 조합이다.
	static int N, M;
	static int[] arr;
	static int[] sel;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		sel = new int[M];
		
		combination(0, 0);
		System.out.println(sb);
	}
	private static void combination(int idx, int k) {
		if(sel.length == k) {
			for(int val : sel) {
				sb.append(val).append(" ");
			}
			sb.append('\n');
			return;
		}
		if(arr.length == idx) return;
		
		
		sel[k] = arr[idx];
		combination(idx+1, k+1);
		combination(idx+1, k);
	}
}

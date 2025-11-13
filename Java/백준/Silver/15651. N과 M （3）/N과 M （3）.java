import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i+1;
		}
		sel = new int[M];
		v = new boolean[N];
		sb = new StringBuilder();
		// 중복순열
		repeatPermutation(0);
		System.out.println(sb);
	}
	
	private static void repeatPermutation(int idx) {
		if(idx == sel.length) {
//			System.out.println(Arrays.toString(sel));
			for(int val : sel) {
				sb.append(val + " ");
			}
			sb.append('\n');
			return;
		}
		
		for (int i = 0; i < N; i++) {
			sel[idx] = arr[i];
			repeatPermutation(idx+1);
		}
	}
	
}

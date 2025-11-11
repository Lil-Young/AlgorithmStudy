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
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for (int i = 1; i < N+1; i++) {
			arr[i-1] = i;
		}
		sel = new int[M];
		v = new boolean[N];
		// 중복 없이 M개를 고른 수열
		// -> 순서가 있으므로 순열
		permutation(0);
		System.out.println(sb);
	}
	private static void permutation(int idx) {
		if(idx == M) {
			for (int val : sel) {
				sb.append(val + " ");
			}
			sb.append('\n');
			return;
		}
		
		
		for(int i=0; i<N; i++) {
			if(!v[i]) {
				v[i] = true;
				sel[idx] = arr[i];
				permutation(idx+1);
				v[i] = false;
			}
		}
	}
}

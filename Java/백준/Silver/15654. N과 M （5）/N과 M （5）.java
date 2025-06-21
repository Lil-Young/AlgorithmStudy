import java.util.*;
import java.io.*;

public class Main {
	// 걍 입력값을 받는 순열이다.
	static int N, M;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
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
		v = new boolean[N];
		permutation(0, 0);
		System.out.println(sb);
	}
	
	private static void permutation(int idx, int k) {
		if(sel.length == k) {
			for(int val : sel) {
				sb.append(val).append(" ");
			}
			sb.append('\n');
			return;
		}
		if(arr.length == idx) return;
		
		
		for(int i=0; i<arr.length; i++) {
			if(!v[i]) {
				v[i] = true;
				sel[k] = arr[i];
				permutation(idx+1, k+1);
				v[i] = false;
			}
		}
	}
}

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] sel;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i=0; i<arr.length; i++) {
			arr[i] = i+1;
		}
		sel = new int[M];
		
		sb = new StringBuilder();
		combination(0, 0);
		System.out.println(sb);
	}
	private static void combination(int idx, int k) {
		if(k == sel.length) {
			for(int val : sel) {
				sb.append(val + " ");
			}
			sb.append('\n');
			return;
		}
		if(idx == arr.length) return;
		
		sel[k] = arr[idx];
		combination(idx+1, k+1);
		combination(idx+1, k);
	}
}

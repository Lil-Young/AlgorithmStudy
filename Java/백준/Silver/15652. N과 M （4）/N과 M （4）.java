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
		sel = new int[M];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		
		sb = new StringBuilder();
		repeatCombination(0, 0);
		System.out.println(sb);
	}
	private static void repeatCombination(int idx, int k) {
		if(sel.length == k) {
//			System.out.println(Arrays.toString(sel));
			for(int val : sel) {
				sb.append(val + " ");
			}
			sb.append('\n');
			return;
		}
		if(arr.length == idx) return;
		
		sel[k] = arr[idx];
		repeatCombination(idx, k+1);
		repeatCombination(idx+1, k);		
	}
}

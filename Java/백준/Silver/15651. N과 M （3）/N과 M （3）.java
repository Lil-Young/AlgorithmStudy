import java.util.*;
import java.io.*;

public class Main {
	// 1부터 N까지 자연수 중에서 M개를 고른 수열
	// 같은 수를 여러번 골라도 된다.
	// 중복순열 인거같다.
	
	static int N, M;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i=1; i<N+1; i++) {
			arr[i-1] = i;
		}
		sel = new int[M];
		v = new boolean[N];
		//중복순열
		repeatPermutation(0);
		System.out.println(sb);
	}
	private static void repeatPermutation(int k) {
		if(sel.length == k) {
//			System.out.println(Arrays.toString(sel));
			for(int val : sel) {
//				System.out.print(val + " ");
				sb.append(val).append(" ");
			}
//			System.out.println();
			sb.append('\n');
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			sel[k] = arr[i];
			repeatPermutation(k+1);
		}
	}
}

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	
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
		
		permutation(0);
	}
	
	private static void permutation(int k) {
		if(sel.length == k) {
//			System.out.println(Arrays.toString(sel));
			for(int val : sel) {
				System.out.print(val + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!v[i]) {
				v[i] = true;
				sel[k] = arr[i];
				permutation(k+1);
				v[i] = false;
			}
		}
	}
}

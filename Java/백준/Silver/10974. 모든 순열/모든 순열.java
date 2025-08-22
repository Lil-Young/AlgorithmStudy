import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		sel = new int[N];
		v = new boolean[N];
		for(int i=1; i<N+1; i++) {
			arr[i-1] = i;
		}
		sb = new StringBuilder();
		permutation(0);
		System.out.println(sb);
	}
	private static void permutation(int k) {
		if(k==sel.length) {
			for(int i=0; i<sel.length; i++) {
				sb.append(sel[i] + " ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!v[i]) {
				sel[k] = arr[i];
				v[i]=true;
				permutation(k+1);
				v[i]=false;
			}
		}
	}
}

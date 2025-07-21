
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int[] n_arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			n_arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(n_arr);
		
		M = Integer.parseInt(br.readLine());
		int[] m_arr = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			sb.append(right(n_arr, target) - left(n_arr, target) + " ");
		}
		System.out.println(sb);
	}
	static int left(int[] arr, int target) {
		int left = 0;
		int right = N;
		while(left < right) {
			int mid = (left+right)/2;
			
			if(arr[mid]>=target) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		return left;
	}
	
	static int right(int[] arr, int target) {
		int left = 0;
		int right = N;
		while(left < right) {
			int mid = (left+right)/2;
			
			if(arr[mid]>target) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		return left;
	}
}


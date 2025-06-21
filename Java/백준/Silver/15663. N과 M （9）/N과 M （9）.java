import java.util.*;
import java.io.*;

public class Main {
	// 그냥 순열인거같은데 ? => 중복된 값은 하나만 출력해야된다.
	static int N, M;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static List<List<Integer>> list;
	static Set<String> set;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		sel = new int[M];
		v = new boolean[N];
		list = new ArrayList<>();
		set = new LinkedHashSet<>();
		permutation(0);
		System.out.println(sb);
	}
	private static void permutation(int k) {
		if(sel.length == k) {
			// 배열 -> 리스트(중복을 비교할 땐, 리스트를 사용한다. 배열은 비교가 안된다.)
//			List<Integer> tempList = new ArrayList<>();
//			for(int val : sel) {
//				tempList.add(val);
//			}
//			if(!list.contains(tempList)) {
//				list.add(tempList);
//				for(int val : sel) {
//					sb.append(val).append(" ");
//				}
//				sb.append("\n");
//			}
//			근데 리스트로 비교하면 모든 리스트를 순회하면서 비교하니깐 시간 복잡도가 O(N!)*M 임
//			그래서 비교할 때는 Set<String> 을 사용해야함
			
			// 배열 -> String
			StringBuilder temp = new StringBuilder();
			for(int val : sel) {
				temp.append(val).append(" ");
			}
			String s = temp.toString();
			
			if(!set.contains(s)) {
				set.add(s);
				for(int val : sel) {
					sb.append(val).append(" ");
				}
				sb.append("\n");
			}
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

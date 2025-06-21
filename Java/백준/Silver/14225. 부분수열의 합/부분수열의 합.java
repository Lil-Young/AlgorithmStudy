import java.io.*;
import java.util.*;

public class Main {
	static int S;
	static int[] arr;
	static boolean[] sel;
	static Set<Integer> set;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[S];
		for(int i=0; i<arr.length; i++) { 
			arr[i] = Integer.parseInt(st.nextToken());
		}
		sel = new boolean[arr.length];
		set = new LinkedHashSet<>();
		recursive(0);		
		// list 정렬
//		System.out.println(set);
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
//		System.out.println(list);
		int minValue = list.get(0);
		int maxValue = list.get(list.size()-1);
		
//		System.out.println(minValue + " " + maxValue);
		for(int i=1; i<=maxValue+1; i++) {
			if(!set.contains(i)) {
				System.out.print(i);
				return;
			}
		}
		
	}
	private static void recursive(int idx) {
		if(arr.length == idx) {
			int sum=0;
			boolean isZero = true;
			for(int i=0; i<arr.length; i++) {
				if(!sel[i]) {
					isZero = false;
					sum+=arr[i];
				}
			}
			// 공집합아닌 값 넣기
			if(!isZero) {
				set.add(sum);	
			}
			return;
		}
		
		
		sel[idx] = true;
		recursive(idx+1);
		sel[idx] = false;
		recursive(idx+1);
	}
}

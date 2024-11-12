import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * N개의 식재료, 식재료들을 각각 N/2개씩 나누어 두 개의 요리를 한다. (N은 짝수)
 * 두 음식을 A, B 라고 하고, A음식과 B음식의 맛의 차이가 최소가 되도록 재료를 분배
 * 
 * 식재료 i는 식재료 j와 같이 요리하게되면 궁합이 잘맞아 시너지 Sij가 발생
 * 각 음식의 맛은 음식을 구성하는 식재료들로부터 발생하는 시너지 Sij들의 합이다.
 * 
 * 식재료 i를 식재료 j와 같이 요리하게 되면 발생하는 시너지 Sij의 정보가 주어지고,
 * 가지고 있는 식재료를 이용해 A음식과 B 음식을 만들 때, 두 음식 간의 맛의 차이가 최소가 되는 경우를 찾고,
 * 그 최솟값을 정답으로 출력하는 프로그램을 작성해라
 * 
 * 
 */

/*
 * 1. 테스트 케이스를 입력받는다
 * 2. N/2의 개수의 크기 만큼 배열을 만든다.
 * 3. 조합으로 N/2 개를 선택한다.
 * 4. 선택된 N/2개의 배열 두 개를 순열로 각 배열의 합을 구하고 절대값으로 서로 두 수를 뺀다.
 * 5. result 보다 작으면 result를 갱신한다.
 */

public class Solution {
	static int N, result;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t < T+1; t++) {
			// 1. 테스트 케이스를 입력받는다
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N][N];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] combiArr = new int[N];
			for (int i = 0; i < combiArr.length; i++) {
				combiArr[i] = i;
			}
			
			// 2. N/2의 개수의 크기 만큼 배열을 만든다.
			Integer[] sel = new Integer[N/2];			
			List<Integer> list = new ArrayList<Integer>();
			// 3. 조합으로 N/2 개를 선택한다.
			combination(list, combiArr, sel,  0, 0);
//			System.out.println(result);
			
			sb.append("#").append(t).append(" ").append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void combination(List<Integer> list, int[] combiArr, Integer[] sel, int idx, int k) {
		// TODO Auto-generated method stub
		if(k == sel.length) {
//			System.out.println(Arrays.toString(sel));
			list = Arrays.asList(sel);
//			System.out.println("list: " + list);
			int[] sel2 = new int[N/2];
			int sel2Idx = 0;
			for (int i = 0; i < N; i++) {
				if(!list.contains(i)) {
					sel2[sel2Idx++] = i;		
				}
			}
//			System.out.println("sel2: " +  Arrays.toString(sel2));
			cal(sel, sel2);
			return;
		}
		if(idx == combiArr.length) return;
		
		sel[k] = combiArr[idx];
		combination(list, combiArr, sel, idx+1, k+1);
		combination(list, combiArr, sel, idx+1, k);
		
	}

	private static void cal(Integer[] sel, int[] sel2) {
		// TODO Auto-generated method stub
		int selSum = 0;
		int sel2Sum = 0;
//		System.out.println(Arrays.toString(sel));
		for (int i = 0; i < sel.length; i++) {
			for (int j = 0; j < sel.length; j++) {
				if(i!=j) {
//					System.out.println(sel[i] + " " + sel[j]);
					int selIdx1 = sel[i];
					int selIdx2 = sel[j];
					selSum+=arr[selIdx1][selIdx2];
					int sel2Idx1 = sel2[i];
					int sel2Idx2 = sel2[j];
					sel2Sum+=arr[sel2Idx1][sel2Idx2];	
				}
			}
		}
		int res = Math.abs(selSum-sel2Sum);
		result = result > res ? res : result;
	}
}



//10
//4
//0 5 3 8
//4 0 4 1
//2 5 0 3
//7 2 3 0
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nArr;
	static int[] mArr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		nArr = new int[N];
		for (int i = 0; i < nArr.length; i++) {
			nArr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nArr);
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		mArr = new int[M];
		for (int i = 0; i < mArr.length; i++) {
			mArr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < mArr.length; i++) {
			System.out.println(bs(mArr[i]));
		}
	}
	private static int bs(int target) {
		int left = 0, right = N-1;
		while(left <= right) {
			int mid = (left+right)/2;
			if(nArr[mid] == target) {
				return 1;
			}
			else if(nArr[mid] < target) {
				left = mid+1;
			}else if(nArr[mid] > target) {
				right = mid-1;
			}
		}
		return 0;
		
	}
}

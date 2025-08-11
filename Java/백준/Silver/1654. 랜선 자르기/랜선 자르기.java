import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long K, N, max;
	static long[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Long.parseLong(st.nextToken());
		N = Long.parseLong(st.nextToken());
		arr = new long[(int) K];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < K; i++) {
			arr[i] = Long.parseLong(br.readLine());
			max = Math.max(max, arr[i]);
		}
		
		long result = bs();
		System.out.println(result);
	}
	
	private static long bs() {
		long start=1;
		long end=max;
		
		while(start<=end) {
			long mid=(start+end)/2;
//			System.out.println(mid);
			long length=0;
			for(long a : arr) {
				length+=(a / mid);
			}
			
//			System.out.println("length: " + length);
			if(length<N) {
				// N보다 길이가 크면 나누는 숫자를 높여야 한다.
				end = mid-1;
			}else {
				// 작으면 나누는 숫자를 줄여야 한다.
				start = mid+1;
			}
		}
		
		return start-1;
	}
}
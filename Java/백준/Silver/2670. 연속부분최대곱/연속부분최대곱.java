import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[] arr = new double[N];
		double value = 1;
		for (int i = 0; i < N; i++) {
			arr[i] = Double.parseDouble(br.readLine());
		}
		double cur = arr[0];
		double max = arr[0];
		for (int i = 1; i < N; i++) {
			cur = Math.max(arr[i], cur*arr[i]);
			max = Math.max(max, cur);
		}
		System.out.printf("%.3f", max);
	}
}

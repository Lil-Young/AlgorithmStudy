import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Character[][] arr = new Character[5][15];
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				arr[i][j] = s.charAt(j);
			}
		}
//		for (int i = 0; i < 5; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 5; j++) {
				if(arr[j][i] != null) {
					System.out.print(arr[j][i]);
				}
			}
		}
	}
}

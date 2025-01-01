import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<String[]> list = new ArrayList<String[]>();
		for (int n = 0; n < N; n++) {
			String s = br.readLine();
			String[] strArr = s.split("");
			list.add(strArr);
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(list.get(i)));
//		}
		
		String[] result = list.get(0);
		for (int i = 1; i < N; i++) {
			String[] str = list.get(i);
			for (int j = 0; j < str.length; j++) {
				if(!result[j].equals(str[j])) {
					result[j] = "?";
				}
			}
		}
//		System.out.println(Arrays.toString(result));
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]);
		}
		
	}

}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		HashMap<String, Integer> map2 = new HashMap<String, Integer>();
		for (int i = 1; i < N+1; i++) {
			String str = br.readLine();
			map.put(i, str);
			map2.put(str, i);
			
		}
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			// 숫자인 경우
//			System.out.println(":::: " + s.charAt(0) + " " + Character.isDigit(s.charAt(0)));
			if(Character.isDigit(s.charAt(0))) {
				// Key 값으로 Value 출력
				System.out.println(map.get(Integer.parseInt(s)));
			}
			// 문자열인 경우
			else {
				// Value 값으로 Key 출력
				System.out.println(map2.get(s));
			}
		}
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// (0~8, 0) map에 저장, 9는 6으로 취급
		for (int i = 0; i < 9; i++) {
			map.put(i, 0);
		}
		String s = br.readLine().trim();
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i)-'0';
			if(val == 9) val = 6;
			map.put(val, map.get(val)+1);
		}
		if(map.get(6) % 2 == 0) {
			map.put(6, map.get(6)/2);	
		}else {
			map.put(6, map.get(6)/2+1);
		}
		
//		System.err.println(map);
		int result = 0;
		for(int n : map.values()) {
			result = Math.max(result, n);
		}
		System.out.println(result);
	}
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			if(!list.contains(s)) {
				list.add(s);	
			}
		}
		Collections.sort(list, (o1, o2) -> {
			if(o1.length() == o2.length()) {
				return o1.compareTo(o2);
			} else {
				return Integer.compare(o1.length(), o2.length());
			}
		});
//		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}

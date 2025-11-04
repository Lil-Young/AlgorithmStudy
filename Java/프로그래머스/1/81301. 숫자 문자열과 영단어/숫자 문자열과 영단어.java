import java.util.*;
import java.io.*;

class Solution {
    static Map<Integer, String> map;
    public int solution(String s) {
        String answer = "";
		
		
		//0	zero
		//1	one
		//2	two
		//3	three
		//4	four
		//5	five
		//6	six
		//7	seven
		//8	eight
		//9	nine
		
		map = new HashMap<Integer, String>();
		map.put(0, "zero");
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
		map.put(6, "six");
		map.put(7, "seven");
		map.put(8, "eight");
		map.put(9, "nine");
		
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			// 숫자일 때
			if(Character.isDigit(s.charAt(i))) {
				answer += s.substring(i, i+1);
			}else {
				str+=s.charAt(i);
				
				if(map.containsValue(str)) {
					// Value로 값을 가져와야됨
					for(Map.Entry<Integer, String> a : map.entrySet()) {
						if(a.getValue().equals(str)) {
							answer += a.getKey();
						}
					}
					str = "";
				}
				
			}
		}
		int num = Integer.parseInt(answer);
		return num;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for (int t = 1; t < T+1; t++) {
            int result = 1;
            Map<Character, Character> map = new HashMap<Character, Character>();
            map.put('{', '}');
            map.put('[', ']');
            map.put('(', ')');
            map.put('<', '>');
//            System.out.println(map.get('{'));
//            System.out.println(map.get('{')=='}');
            Stack<Character> stack = new Stack<>();
            
            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();

            for (int i = 0; i < str.length(); i++) {
                char s = str.charAt(i);
                
                if(stack.isEmpty() && (s=='}' || s==']' || s==')' || s=='>')) {
                	result=0;
                	break;
                }
                
                if(s=='{' || s=='[' || s=='(' || s=='<') {
                    stack.push(s);
                }
                
                else {
                    char before = stack.pop(); // '(' 와 같은 문자
                    if(map.get(before) != s) {
                        result = 0;
                        break;
                    }
                }
            }
//            System.out.println((stack));
            
            
            System.out.println("#"+t+" "+result);
        }
    }

}
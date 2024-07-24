import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] arr;
    static int N;
    static int result;

    private static void recursive(int val1, int val2, int k, int d, boolean[] v) {
        if (k == N) {
            d += Math.abs(val1 - arr[N + 1][0]) + Math.abs(val2 - arr[N + 1][1]);
            result = Math.min(result, d);
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!v[i]) {
                v[i] = true;
                recursive(arr[i][0], arr[i][1], k+1, d + Math.abs(val1 - arr[i][0]) + Math.abs(val2 - arr[i][1]), v);
                v[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int t = 1; t <= TC; t++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[N + 2][2];
            
            arr[0][0] = Integer.parseInt(st.nextToken());
            arr[0][1] = Integer.parseInt(st.nextToken());
            
            arr[N+1][0] = Integer.parseInt(st.nextToken());
            arr[N+1][1] = Integer.parseInt(st.nextToken());
            
            for (int i = 1; i <= N; i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            int val1 = arr[0][0];
            int val2 = arr[0][1];
            boolean[] v = new boolean[N + 1];
            result = Integer.MAX_VALUE;

            recursive(val1, val2, 0, 0, v);

            System.out.println("#" + t + " " + result);
        }
    }
}

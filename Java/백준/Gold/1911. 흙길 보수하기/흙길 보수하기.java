import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        long pos = 0;
        long answer = 0;

        for (int i = 0; i < N; i++) {
            int s = arr[i][0];
            int e = arr[i][1];

            if (pos < s) pos = s;

            if (pos < e) {
                long needLen = e - pos;
                long boards = (needLen + L - 1) / L;
                answer += boards;
                pos += boards * (long)L;
            }
        }

        System.out.println(answer);
    }
}
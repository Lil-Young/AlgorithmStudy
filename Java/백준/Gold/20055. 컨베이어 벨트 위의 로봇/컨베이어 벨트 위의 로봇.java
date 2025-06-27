import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, result;
    static int[] arr;
    static boolean[] robot;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        result = 0;

        while (true) {
            result++;

            // 벨트 및 로봇 회전
            move();

            // 로봇 이동
            moveRobots();

            // 로봇 올리기
            if (arr[0] > 0 && !robot[0]) {
                robot[0] = true;
                arr[0]--;
            }

            // 내구도 0인 칸 수 세기
            if (zeroCount() >= K) break;
        }

        System.out.println(result);
    }

    private static void move() {
        // 벨트 회전
        int last = arr[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = last;

        for (int i = N - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false;

        // 로봇이 내리는 위치에 도달하면 제거
        robot[N - 1] = false;
    }

    private static void moveRobots() {
        for (int i = N - 2; i >= 0; i--) {
            if (robot[i] && !robot[i + 1] && arr[i + 1] > 0) {
                robot[i] = false;
                robot[i + 1] = true;
                arr[i + 1]--;
            }
        }
        robot[N - 1] = false;
    }

    private static int zeroCount() {
        int count = 0;
        for (int a : arr) {
            if (a == 0) count++;
        }
        return count;
    }
}

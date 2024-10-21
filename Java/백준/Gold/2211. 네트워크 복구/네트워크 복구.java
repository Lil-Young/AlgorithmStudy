import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int vertex, weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);  // 가중치가 작은 순서대로 우선순위 큐에서 나오도록 설정
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 컴퓨터 수 (정점 수)
        int M = Integer.parseInt(st.nextToken());  // 연결된 회선 수 (간선 수)

        // 인접 리스트 생성
        Node[] adjList = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);  // 무향 그래프이므로 양쪽으로 연결
        }

        // 다익스트라 알고리즘을 통한 최단 거리 계산 및 경로 복구
        dijkstra(N, adjList);
    }

    static void dijkstra(int N, Node[] adjList) {
        final int INF = Integer.MAX_VALUE;
        int[] minDistance = new int[N + 1];  // 최단 거리를 저장하는 배열
        int[] prev = new int[N + 1];         // 경로 복구를 위한 이전 정점 기록
        boolean[] visited = new boolean[N + 1];  // 방문 체크 배열

        Arrays.fill(minDistance, INF);
        minDistance[1] = 0;  // 시작점은 1번 컴퓨터로 설정

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));  // 시작 정점

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currVertex = current.vertex;

            if (visited[currVertex]) continue;
            visited[currVertex] = true;

            // 인접 정점 탐색
            for (Node temp = adjList[currVertex]; temp != null; temp = temp.next) {
                if (!visited[temp.vertex] && minDistance[temp.vertex] > minDistance[currVertex] + temp.weight) {
                    minDistance[temp.vertex] = minDistance[currVertex] + temp.weight;
                    pq.offer(new Node(temp.vertex, minDistance[temp.vertex]));
                    prev[temp.vertex] = currVertex;  // 이전 정점을 기록
                }
            }
        }

        // 경로 복구 출력
        System.out.println(N - 1);  // 필요한 간선의 개수는 N-1개
        for (int i = 2; i <= N; i++) {
            System.out.println(i + " " + prev[i]);  // 각 정점 i에 대해 이전 정점을 출력
        }
    }
}

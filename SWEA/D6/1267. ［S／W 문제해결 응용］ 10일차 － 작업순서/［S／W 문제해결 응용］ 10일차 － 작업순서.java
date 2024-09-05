import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 그래프 -> 위상정렬
// 그래프 저장 방법? 인접리스트
public class Solution {

	private static class Node {
		public int vertex;
		public Node link;

		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
	}

	private static int V, E; // 정점 개수, 간선 개수
	private static Node[] adjList; // 인접리스트
	private static int[] inDegree; // 인덱스 번호: 정점번호, 값: 진입차수

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T;
		T = 10; // 문제에서 테스트 케이스 10개 고정
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			adjList = new Node[V + 1]; // 정점 번호는 1부터 시작 (0번 사용 안함)
			inDegree = new int[V + 1]; // 각 정점의 진입차수 기록

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				inDegree[to]++;
			}

			List<Integer> list = topologySort();

			// 정답 출력
			// 큐가 비어있으면 정상적으로 작업이 수행됨
			// orderList의 원소 개수가 V개이면 정상적으로 작업이 수행됨
			if (list.size() == V) {
				for (Integer vertex : list) {
					sb.append(vertex + " ");
				}
				sb.append("\n");
			}
			// 큐가 비어있지 않으면 싸이클 발생한 경우 이므로 답이 될 수 없다.
			else {
				System.out.println("cycle"); // 이 문제에서는 싸이클 발생 안한다고 했으니 이 코드가 실행될 경우는 없음
			}
		}

		System.out.println(sb);
	}

	private static List<Integer> topologySort() {

		List<Integer> orderList = new ArrayList<Integer>();

		Queue<Integer> q = new ArrayDeque<Integer>();
		for (int i = 1; i <= V; i++) {

			// 1. 진입 차수가 0인 노드(시작점)를 큐에 모두 넣는다.
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {

			// 2. 큐에서 진입 차수가 0인 노드를 꺼내어 자신과 인접한 노드의 간선을 제거한다.
			Integer cur = q.poll();
			orderList.add(cur); // 큐에서 나온 정점은 처리한 작업이다.

			for (Node temp = adjList[cur]; temp != null; temp = temp.link) { // 현재 정점 cur를 기준으로 인접한 정점 하나씩 가져오기

				// 2-1. 인접한 노드의 진입 차수를 1 감소시킨다.
				if (--inDegree[temp.vertex] == 0) {

					// 3. 간선 제거 후 진입차수가 0이 된 노드를 큐에 넣는다.
					q.offer(temp.vertex);
				}
			}
		}

		return orderList;
	}

}
import java.util.Arrays;

public class LinkStateRouting {

    private static final int V = 5;  // Number of vertices (or routers)
    private static final int INF = 9999;  // Infinity

    int minDistance(int[] dist, boolean[] sptSet) {
        int min = INF, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        Arrays.fill(dist, INF);
        Arrays.fill(sptSet, false);

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("Vertex \t Distance from Source " + src);
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, INF, 1, INF},
                {2, 0, 3, INF, INF},
                {INF, 3, 0, 2, 1},
                {1, INF, 2, 0, 3},
                {INF, INF, 1, 3, 0}
        };

        LinkStateRouting lsr = new LinkStateRouting();
        lsr.dijkstra(graph, 0);
    }
}

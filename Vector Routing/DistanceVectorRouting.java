import java.util.Arrays;
//Distance Vector Routing Algorithm using Bellman Ford's Algorithm
public class DistanceVectorRouting {

    private static final int V = 5;  // Number of vertices (or routers)
    private static final int INF = 999;  // Infinity representation

    static class Node {
        int[] distance = new int[V];
        int[] nextHop = new int[V];
    }

    public static void bellmanFord(int[][] graph) {
        Node[] routingTable = new Node[V];

        // Initialize routing table for each node
        for (int i = 0; i < V; i++) {
            routingTable[i] = new Node();
            for (int j = 0; j < V; j++) {
                routingTable[i].distance[j] = graph[i][j];
                routingTable[i].nextHop[j] = j;
            }
        }

        // Relax all edges V-1 times (Bellman-Ford)
        for (int k = 0; k < V - 1; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    for (int v = 0; v < V; v++) {
                        if (routingTable[i].distance[v] > graph[i][j] + routingTable[j].distance[v]) {
                            routingTable[i].distance[v] = graph[i][j] + routingTable[j].distance[v];
                            routingTable[i].nextHop[v] = j;
                        }
                    }
                }
            }
        }

        // Print the final routing table
        for (int i = 0; i < V; i++) {
            System.out.println("Routing table for router " + (i+1) + ":");
            System.out.println("Destination\tNext Hop\tDistance");
            for (int j = 0; j < V; j++) {
                System.out.println(j + "\t\t\t" + routingTable[i].nextHop[j] + "\t\t\t" + routingTable[i].distance[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Graph representation of network
        int[][] graph = {
                {0, 2, INF, 1, INF},
                {2, 0, 3, INF, INF},
                {INF, 3, 0, 2, 1},
                {1, INF, 2, 0, 3},
                {INF, INF, 1, 3, 0}
        };
        bellmanFord(graph);
    }
}

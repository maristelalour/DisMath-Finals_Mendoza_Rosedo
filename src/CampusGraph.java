import java.util.ArrayList;
import java.util.List;

/**
 * ===== TOPIC: GRAPH THEORY (Dijkstra's Shortest Path) =====
 *
 * The campus is modeled as a WEIGHTED, UNDIRECTED graph:
 *   - Vertices (nodes) = buildings
 *   - Edges            = walkways between buildings
 *   - Weights          = walking distance in meters
 *
 * Represented as an ADJACENCY MATRIX (2D array):
 *   distances[i][j] holds the distance between building i and j.
 *   If there is NO direct connection, the value is INF.
 */
// Graph Theory
public class CampusGraph {

    private static final int INF = Integer.MAX_VALUE;

    private String[] buildings;
    private int[][] distances;
    private int n;

    public CampusGraph() {
        // Step 1: Name the 5 campus buildings.
        buildings = new String[]{
                "Main Building",  // index 0
                "Library",        // index 1
                "Canteen",        // index 2
                "IT Building",    // index 3
                "Gymnasium"       // index 4
        };

        // Step 2: Set n and create the adjacency matrix.
        n = buildings.length;
        distances = new int[n][n];

        // Step 3: Fill matrix — 0 on diagonal, INF everywhere else.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = (i == j) ? 0 : INF;
            }
        }

        // Step 4: Add at least 6 edges (undirected, in meters).
        addEdge(0, 1, 100);   // Main Building <-> Library       : 100 m
        addEdge(0, 2, 150);   // Main Building <-> Canteen       : 150 m
        addEdge(1, 3,  80);   // Library       <-> IT Building   :  80 m
        addEdge(2, 3, 120);   // Canteen       <-> IT Building   : 120 m
        addEdge(2, 4, 200);   // Canteen       <-> Gymnasium     : 200 m
        addEdge(3, 4,  90);   // IT Building   <-> Gymnasium     :  90 m
    }

    private void addEdge(int a, int b, int weight) {
        // Undirected edge: record the weight in both directions.
        distances[a][b] = weight;
        distances[b][a] = weight;
    }

    public String[] getBuildings() {
        return buildings;
    }

    public void displayMap() {
        // Print the list of buildings with their index numbers.
        System.out.println("--- Campus Buildings ---");
        for (int i = 0; i < n; i++) {
            System.out.println("[" + i + "] " + buildings[i]);
        }

        // Print each direct connection once (j = i+1 avoids printing the same edge twice).
        System.out.println("\n--- Direct Connections ---");
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (distances[i][j] != INF) {
                    System.out.println(buildings[i] + " <-> " + buildings[j]
                            + " (" + distances[i][j] + "m)");
                }
            }
        }
    }

    // ===== Dijkstra's Algorithm =====
    /**
     * Dijkstra's Algorithm — finds the shortest path from source to dest.
     */
    public void shortestPath(int source, int dest) {

        // Special case: source and destination are the same building.
        if (source == dest) {
            System.out.println("Shortest distance: 0 meters");
            System.out.println("Path: " + buildings[source]);
            return;
        }

        // ---- Step 1: Initialize arrays ----
        int[] dist     = new int[n];      // shortest known distance from source
        int[] previous = new int[n];      // which vertex we came from
        boolean[] visited = new boolean[n]; // whether a vertex is finalized

        for (int i = 0; i < n; i++) {
            dist[i]     = INF;   // unknown distance at start
            previous[i] = -1;   // no predecessor yet
            visited[i]  = false;
        }
        dist[source] = 0;  // distance from source to itself is 0

        // ---- Step 2: Relax edges n times ----
        for (int count = 0; count < n; count++) {

            // (a) Pick the unvisited vertex u with the smallest dist[u].
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }

            // If every remaining vertex is unreachable, stop early.
            if (u == -1 || dist[u] == INF) break;

            // (b) Mark u as finalized.
            visited[u] = true;

            // (c) Update distances to all unvisited neighbors of u.
            for (int v = 0; v < n; v++) {
                if (!visited[v] && distances[u][v] != INF) {
                    int newDist = dist[u] + distances[u][v];
                    if (newDist < dist[v]) {
                        dist[v]     = newDist;
                        previous[v] = u;
                    }
                }
            }
        }

        // ---- Step 3: Print the result ----
        if (dist[dest] == INF) {
            System.out.println("No path found between "
                    + buildings[source] + " and " + buildings[dest] + ".");
            return;
        }

        // Reconstruct the path by walking backwards from dest using previous[].
        List<Integer> path = new ArrayList<>();
        for (int v = dest; v != -1; v = previous[v]) {
            path.add(0, v);   // insert at front to reverse the order
        }

        // Print the total distance and the full path.
        System.out.println("Shortest distance: " + dist[dest] + " meters");
        StringBuilder sb = new StringBuilder("Path: ");
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(buildings[path.get(i)]);
        }
        System.out.println(sb.toString());
    }
}
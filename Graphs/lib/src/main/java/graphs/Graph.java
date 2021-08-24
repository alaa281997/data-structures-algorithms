package main.java.graphs;

import java.util.*;

public class Graph {

    private final Map<Vertex, List<Vertex>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    public void addVertex(String data) {
        Vertex vertex = new Vertex(data);
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String data1, String data2) {
        Vertex vertex1 = new Vertex(data1);
        Vertex vertex2 = new Vertex(data2);

        adjVertices.get(vertex1).add(vertex2);
        adjVertices.get(vertex2).add(vertex1);
    }

    public void removeVertex(String data) {
        Vertex vertex = new Vertex(data);
        adjVertices.values().forEach(list -> list.remove(vertex));
        adjVertices.remove(vertex);
    }

    public void removeEdge(String data1, String data2) {
        Vertex vertex1 = new Vertex(data1);
        Vertex vertex2 = new Vertex(data2);

        List<Vertex> edgeVertex1 = adjVertices.get(vertex1);
        List<Vertex> edgeVertex2 = adjVertices.get(vertex2);

        if (edgeVertex1 != null) {
            edgeVertex1.remove(vertex2);
        }

        if (edgeVertex2 != null) {
            edgeVertex2.remove(vertex1);
        }
    }

    public String printGraph() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Vertex vertex : adjVertices.keySet()) {
            stringBuilder.append(vertex);
            stringBuilder.append(adjVertices.get(vertex));
        }

        return stringBuilder.toString();
    }

    public int size() {
        return adjVertices.size();
    }

    Set<String> dft(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);

                for (Vertex v : graph.getNeighbors(vertex)) {
                    stack.push(v.data);
                }
            }
        }

        return visited;
    }

    public List<Vertex> getNeighbors(String data) {
        return adjVertices.get(new Vertex(data));
    }

    public Set<Vertex> getVertices(){
        return adjVertices.keySet();
    }

}

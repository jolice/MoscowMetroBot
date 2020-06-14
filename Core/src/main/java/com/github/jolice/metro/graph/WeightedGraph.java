package com.github.jolice.metro.graph;

import com.github.jolice.metro.graph.api.Vertex;
import com.github.jolice.metro.graph.api.AdjacencyMatrix;
import com.github.jolice.metro.graph.api.Graph;
import com.github.jolice.metro.graph.api.Indexed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WeightedGraph<V extends Indexed> implements Graph<V> {

    private List<Vertex<V>> vertexes = new ArrayList<>();
    private AdjacencyMatrix adjacencyMatrix;

    public WeightedGraph(AdjacencyMatrix adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    @Override
    public int getEdge(Indexed first, Indexed second) {
        return adjacencyMatrix.get(first.index(), second.index());
    }

    @Override
    public void addEdge(Indexed first, Indexed second, int weight) {
        this.adjacencyMatrix.set(first.index(), second.index(), weight);
    }

    @Override
    public void addVertex(Vertex<V> vertex) {
        this.vertexes.add(vertex);
    }

    @Override
    public AdjacencyMatrix getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    @Override
    public List<Vertex<V>> getVertexes() {
        return Collections.unmodifiableList(vertexes);
    }

    @Override
    public int getVertexCount() {
        return vertexes.size();
    }

    @Override
    public void reset() {
        vertexes.forEach(x -> x.setVisited(false));
    }
}

package io.riguron.metro.graph.api;

import java.util.List;

public interface Graph<V extends Indexed> {

    int getEdge(Indexed first, Indexed second);

    void addEdge(Indexed first, Indexed second, int weight);

    void addVertex(Vertex<V> vertex);

    AdjacencyMatrix getAdjacencyMatrix();

    List<Vertex<V>> getVertexes();

    int getVertexCount();

    void reset();
}

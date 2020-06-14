package com.github.jolice.metro.graph.api;

public interface AdjacencyMatrix {

    void set(int a, int b, int weight);

    int get(int vertex, int neighbour);

    int[] getAdjacentNodes(int row);


}

package io.riguron.metro.graph;

import io.riguron.metro.graph.api.AdjacencyMatrix;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.metro.graph.api.AdjacencyMatrix;
import io.riguron.metro.graph.api.AdjacencyMatrix;

@EqualsAndHashCode
@ToString
public class PlainAdjacencyMatrix implements AdjacencyMatrix {

    private int[][] matrix;

    public PlainAdjacencyMatrix(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Matrix length must be positive!");
        }
        this.matrix = new int[size][size];
    }

    @Override
    public void set(int a, int b, int weight) {
        this.multidimensionalRangeCheck(a, b);
        this.matrix[a][b] = weight;
    }

    @Override
    public int get(int vertex, int neighbour) {
        this.multidimensionalRangeCheck(vertex, neighbour);
        return this.matrix[vertex][neighbour];
    }

    @Override
    public int[] getAdjacentNodes(int row) {
        this.rangeCheckLength(matrix.length, row);
        return this.matrix[row].clone();
    }

    private void multidimensionalRangeCheck(int a, int b) {
        this.rangeCheckLength(matrix.length, a);
        this.rangeCheckLength(matrix[a].length, b);
    }

    private void rangeCheckLength(int length, int x) {
        if (x < 0 || x >= length) {
            throw new IllegalArgumentException(String.format("Out of range: (setIndex: %d, length: %d)", x, length));
        }
    }
}

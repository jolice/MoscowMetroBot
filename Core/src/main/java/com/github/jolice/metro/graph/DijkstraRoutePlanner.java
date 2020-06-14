package com.github.jolice.metro.graph;

import com.github.jolice.metro.graph.api.Vertex;
import com.github.jolice.metro.route.RoutePlanner;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.jolice.metro.graph.api.Graph;
import com.github.jolice.metro.graph.api.Indexed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DijkstraRoutePlanner<T extends Indexed> implements RoutePlanner<T> {

    private Graph<T> graph;
    private List<DijkstraVertex<T>> vertexes;

    @Autowired
    public DijkstraRoutePlanner(Graph<T> graph) {
        this.graph = graph;
    }

    @Override
    public List<T> route(Indexed from, Indexed to) {
        return getPath(from.index(), to.index());
    }


    private List<T> getPath(int from, int target) {

        List<T> list = new ArrayList<>();

        this.execute(from);
        this.graph.reset();

        DijkstraVertex<T> step = this.vertexes.get(target);

        if (step.getParent() == null) {
            return list;
        }

        list.add(step.getItem());

        while (step.getParent() != null) {
            step = step.getParent();
            list.add(step.getItem());
        }

        Collections.reverse(list);
        return Collections.unmodifiableList(list);
    }


    private void execute(int source) {

        List<Vertex<T>> graphVertexes = graph.getVertexes();
        int vertexCount = graph.getVertexCount();
        this.vertexes = new ArrayList<>();


        for (int i = 0; i < vertexCount; i++) {
            DijkstraVertex<T> dijkstraVertex = new DijkstraVertex<>(graphVertexes.get(i));
            dijkstraVertex.setDistance(Integer.MAX_VALUE);
            vertexes.add(dijkstraVertex);
        }


        vertexes.get(source).setDistance(0);

        for (int i = 0; i < vertexCount; i++) {

            int next = getMinimum();

            this.visitNeighbours(next);


        }

    }

    private void visitNeighbours(int node) {
        DijkstraVertex<T> entityNode = this.vertexes.get(node);
        List<Integer> adjacentNodes = getNeighbours(node);
        for (Integer target : adjacentNodes) {

            int distanceToCurrent = entityNode.getDistance();

            int distanceFromCurrentToTarget = this.graph.getAdjacencyMatrix().get(node, target);

            int totalDistanceToTarget = distanceToCurrent + distanceFromCurrentToTarget;
            DijkstraVertex<T> targetVertex = this.vertexes.get(target);

            if (targetVertex.getDistance() > totalDistanceToTarget) {

                targetVertex.setDistance(totalDistanceToTarget);
                targetVertex.setParent(entityNode);
            }
        }
        entityNode.setVisited(true);
    }

    private List<Integer> getNeighbours(int node) {
        List<Integer> neighbors = new ArrayList<>();

        int[] graphNeighbours = this.graph.getAdjacencyMatrix().getAdjacentNodes(node);


        for (int i = 0; i < graphNeighbours.length; i++) {

            if (graphNeighbours[i] > 0) {
                Vertex<T> neighbour = this.vertexes.get(i);
                if (!neighbour.isVisited()) {
                    neighbors.add(i);
                }
            }
        }
        return neighbors;
    }


    private int getMinimum() {
        int min = -1;
        for (int i = 0; i < this.vertexes.size(); i++) {
            DijkstraVertex next = this.vertexes.get(i);
            if (!next.isVisited()) {
                if (min == -1) {
                    min = i;
                } else {
                    if (next.getDistance() < this.vertexes.get(min).getDistance()) {
                        min = i;
                    }
                }
            }
        }
        return min;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    private static class DijkstraVertex<T extends Indexed> extends Vertex<T> {

        private DijkstraVertex<T> parent;
        private int distance;

        private DijkstraVertex(Vertex<T> vertex) {
            super(vertex.getItem());
        }

    }

}

package Challenge20;

import java.util.ArrayList;
import java.util.Objects;

public class Edge {
    private ArrayList<Boolean> list;

    public Edge(ArrayList<Boolean> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(list, edge.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "list=" + list +
                '}';
    }
}

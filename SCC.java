import java.util.ArrayList;

public class SCC {
    public ArrayList<Integer> nodes;

    public SCC(ArrayList<Integer> nodes) {
        this.nodes = nodes;
    }
    public void add(int node) {
        nodes.add(node);
    }
    public int getFirst() {
        return nodes.get(0);
    }
    public String toString() {
        return nodes.toString();
    }
    public int size() {
        return nodes.size();
    }
    public Integer get(int j) {
        return nodes.get(j);
    }
}

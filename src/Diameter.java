import java.util.Vector;

public class Diameter {
    private boolean[][] mat;
    int _result;
    
    public Diameter(boolean[][] adj_mat) {
        mat = adj_mat;
        int nodes = mat.length;
        var tree = new Vector[nodes];
        for (int i = 0; i < nodes; i++) {
            tree[i] = new Vector(nodes);
        }
        for (int i = 0; i < nodes; i++) {
            for (int j = i+1; j < nodes; j++) {
                if (mat[i][j]) {
                    tree[i].add(j);
                    tree[j].add(i);
                }
            }
        }
        _result = burn_and_calculate(tree);
    }

    public static int burn_and_calculate(Vector[] tree) {
        int nodes = tree.length;
        int vertex = 0;
        int leaf = 0;
        Vector<Integer> leaves = new Vector<>(nodes);
        int radius = 0, diameter;
        int[] degrees = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            degrees[i] = tree[i].size();
            if (degrees[i] == 1) {
                leaves.add(i);
            }
        }
        while (nodes > 2) {
            Vector<Integer> temp = new Vector<>(nodes);
            for (Integer integer : leaves) {
                leaf = integer;
                degrees[leaf] = 0;
                for (int j = 0; j < tree[leaf].size(); j++) {
                    vertex = (int) tree[leaf].get(j);
                    if (degrees[vertex] > 0) {
                        degrees[vertex]--;
                        if (degrees[vertex] == 1) temp.add(vertex);
                    }
                }
                nodes--;
            }
            leaves = temp;
            radius++;
        }
        if (leaves.size() == 2) {
            radius++;
            diameter = radius * 2 - 1;
        }
        else diameter = radius * 2;
        return diameter;
    }

    public int get_diam() {
        return _result;
    }
}

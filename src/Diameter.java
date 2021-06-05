import java.util.Vector;

public class Diameter {
    Vector[] _tree;
    int _result;
    
    public Diameter(boolean[][] adj_mat) {
        int nodes = adj_mat.length;
        _tree = new Vector[nodes];
        for (int i = 0; i < nodes; i++) {
            _tree[i] = new Vector(nodes);
        }
        for (int i = 0; i < nodes; i++) {
            for (int j = i+1; j < nodes; j++) {
                if (adj_mat[i][j]) {
                    _tree[i].add(j);
                    _tree[j].add(i);
                }
            }
        }
    }

    public void burn_and_calculate() {
        int nodes = _tree.length;
        int vertex = 0;
        int leaf = 0;
        Vector<Integer> leaves = new Vector<>(nodes);
        int radius = 0;
        int[] degrees = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            degrees[i] = _tree[i].size();
            if (degrees[i] == 1) leaves.add(i);
        }
        while (nodes > 2) {
            Vector<Integer> temp = new Vector<>(nodes);
            for (Integer integer : leaves) {
                leaf = integer;
                degrees[leaf] = 0;
                for (int j = 0; j < _tree[leaf].size(); j++) {
                    vertex = (int) _tree[leaf].get(j);
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
            _result = radius * 2 - 1;
        }
        else _result = radius * 2;
    }

    public int get_diam() {
        burn_and_calculate();
        return _result;
    }
}
